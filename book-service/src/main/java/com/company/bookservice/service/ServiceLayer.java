package com.company.bookservice.service;

import com.company.bookservice.DAO.BookDao;
import com.company.bookservice.DTO.Book;
import com.company.bookservice.ViewModel.ViewModel;
import com.company.bookservice.util.feign.NoteClient;
import com.company.bookservice.util.messages.Note;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceLayer {

    //Feign Client
    private NoteClient client;

    private BookDao dao;

    private RabbitTemplate rabbitTemplate;
    //Rabbit MQ Set up
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.controller";

    @Autowired
    public ServiceLayer(NoteClient client, BookDao dao, RabbitTemplate rabbitTemplate) {
        this.client = client;
        this.dao = dao;
        this.rabbitTemplate = rabbitTemplate;
    }

    //    public Book addBook(Book book);
    @Transactional
    public ViewModel saveBook(ViewModel viewModel){
        Book book = new Book(viewModel.getTitle(),viewModel.getAuthor());
        book = dao.addBook(book);
        int myBookId = book.getBookId();
        viewModel.setBookId(myBookId);
        List<Note> notes = viewModel.getNotes();
        notes.stream().forEach(note -> {
            note.setBookId(myBookId);
            rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY,note);});
        return viewModel;
    }

    private ViewModel buildViewModel(Book book){
        List<Note> notes = getNotesByBook(book.getBookId());
        ViewModel viewModel = new ViewModel(book.getBookId(),book.getTitle(),book.getAuthor(),notes);
        return viewModel;
    }

    //    public Book getBook(int bookId);
    public ViewModel findBook(int id){
        return buildViewModel(dao.getBook(id));
    }

    //    public List<Book> getAllBooks();
    public List<ViewModel> findAllBooks(){

        return dao.getAllBooks().stream()
                .map(this::buildViewModel)
                .collect(Collectors.toList());

    }

    //    public void updateBook(Book book);
    @Transactional
    public void updateBook(ViewModel viewModel){
        Book book = new Book(viewModel.getBookId(),viewModel.getTitle(),viewModel.getAuthor());
        dao.updateBook(book);
        int myBookId = book.getBookId();
        List<Note> notes = viewModel.getNotes();
        notes.stream().forEach(note -> {
            note.setBookId(myBookId);
            rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY,note);});
    }

    //    public void deleteBook(int bookId);
    @Transactional
    public void removeBook(int id){

        getNotesByBook(id)
                .stream()
                .forEach(note -> client.deleteNoteFromDB(note.getNoteId()));
        dao.deleteBook(id);

    }

    public Note getNote(int noteId){
        return client.getNoteFromDB(noteId);
    }

    public List<Note> getAllNotes(){
        List<Note> notes = client.getNoteListfromDB();
        return notes;
    }

    public List<Note> getNotesByBook(int bookId){
        List<Note> notes = client.getNotesByBookfromDB(bookId);
        return notes;
    }

    public void createNote(Note note){
        rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY,note);
    }

    public void updateNote(Note note){
        rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY,note);
    }

    public void deleteNote(int id){
        client.deleteNoteFromDB(id);
    }


}
