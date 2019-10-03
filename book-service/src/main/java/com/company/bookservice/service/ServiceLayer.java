package com.company.bookservice.service;

import com.company.bookservice.DAO.BookDao;
import com.company.bookservice.DTO.Book;
import com.company.bookservice.ViewModel.ViewModel;
import com.company.bookservice.util.feign.NoteClient;
import com.company.bookservice.util.messages.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ServiceLayer {


    NoteClient client;

    private BookDao dao;

    @Autowired
    public ServiceLayer(BookDao dao, NoteClient client){
        this.dao = dao;
        this.client = client;
    }

    @Transactional
    public ViewModel saveBook(ViewModel viewModel){
        return null;
    }

    private ViewModel buildViewModel(Book book){
        List<Note> notes = getNotesByBook(book.getBookId());
        ViewModel viewModel = new ViewModel(book.getBookId(),book.getTitle(),book.getAuthor(),notes);
        return viewModel;
    }

    public ViewModel findBook(int id){
        return buildViewModel(dao.getBook(id));
    }

    public List<ViewModel> findAllBooks(){

    }

    @Transactional
    public void updateBook(ViewModel viewModel){

    }

    @Transactional
    public void removeBook(int id){

    }

    public List<Note> getNotesByBook(int bookId){
        List<Note> notes = client.getNotesByBookfromDB(bookId);
        return notes;
    }

}
