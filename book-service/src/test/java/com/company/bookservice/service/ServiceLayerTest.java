package com.company.bookservice.service;

import com.company.bookservice.DAO.BookDao;
import com.company.bookservice.DAO.BookImplemention;
import com.company.bookservice.DTO.Book;
import com.company.bookservice.ViewModel.ViewModel;
import com.company.bookservice.util.feign.NoteClient;
import com.company.bookservice.util.messages.Note;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    private ServiceLayer serviceLayer;
    private BookDao bookDao;
    private NoteClient client;
    private RabbitTemplate rabbitTemplate;

    private void setUpNoteClientMock(){
        client = mock(NoteClient.class);

        Note note = new Note();
        note.setNoteId(1);
        note.setBookId(1);
        note.setNote("Sample Note 1");

        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Sample Note 1");

        List<Note> nList = new ArrayList<>();
        nList.add(note);

        doReturn(nList).when(client).getNotesByBookfromDB(1);
    }

    private void setUpBookDaoMock(){
        bookDao = mock(BookImplemention.class);

        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Sample Title");
        book.setAuthor("Sample Author");

        Book book2 = new Book();
        book2.setTitle("Sample Title");
        book2.setAuthor("Sample Author");

        List<Book> bList = new ArrayList<>();
        bList.add(book);

        doReturn(book).when(bookDao).addBook(book2);
        doReturn(book).when(bookDao).getBook(1);
        doReturn(bList).when(bookDao).getAllBooks();
    }

    @Before
    public void setUp() throws Exception {
        setUpBookDaoMock();
        serviceLayer = new ServiceLayer(client, bookDao, rabbitTemplate);
    }

    @Test
    public void saveBook() {
        List<Note> noteList = new ArrayList<>();
        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Sample Note 1");

        noteList.add(note2);

        ViewModel viewModel2 = new ViewModel();
        viewModel2.setTitle("Sample Title");
        viewModel2.setAuthor("Sample Author");
        viewModel2.setNotes(noteList);

        viewModel2 = serviceLayer.saveBook(viewModel2);

        assertEquals(viewModel2, serviceLayer.findBook(viewModel2.getBookId()));

    }

    @Test
    public void findBook() {
        List<Note> noteList = new ArrayList<>();
        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Sample Note 1");

        noteList.add(note2);

        ViewModel viewModel2 = new ViewModel();
        viewModel2.setTitle("Sample Title");
        viewModel2.setAuthor("Sample Author");
        viewModel2.setNotes(noteList);

        viewModel2 = serviceLayer.saveBook(viewModel2);

        assertEquals(viewModel2, serviceLayer.findBook(viewModel2.getBookId()));

    }

    @Test
    public void findAllBooks() {
        List<Note> noteList = new ArrayList<>();
        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Sample Note 1");

        noteList.add(note2);

        ViewModel viewModel2 = new ViewModel();
        viewModel2.setTitle("Sample Title");
        viewModel2.setAuthor("Sample Author");
        viewModel2.setNotes(noteList);

        serviceLayer.saveBook(viewModel2);

        assertEquals(1, serviceLayer.findAllBooks().size());

    }

    @Test
    public void updateBook() {


        List<Note> noteList = new ArrayList<>();
        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Sample Note 1");

        noteList.add(note2);

        ViewModel viewModel2 = new ViewModel();
        viewModel2.setBookId(1);
        viewModel2.setTitle("Sample Title");
        viewModel2.setAuthor("Sample Author");
        viewModel2.setNotes(noteList);

        ArgumentCaptor<ViewModel> bookCaptor = ArgumentCaptor.forClass(ViewModel.class);
        doNothing().when(serviceLayer).updateBook(bookCaptor.capture());

        serviceLayer.updateBook(viewModel2);

        verify(serviceLayer, times(1)).updateBook(bookCaptor.getValue());
        assertEquals(viewModel2, bookCaptor.getValue());

    }

    @Test
    public void removeBook() {

        List<Note> noteList = new ArrayList<>();
        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Sample Note 1");

        noteList.add(note2);

        ViewModel viewModel2 = new ViewModel();
        viewModel2.setBookId(1);
        viewModel2.setTitle("Sample Title");
        viewModel2.setAuthor("Sample Author");
        viewModel2.setNotes(noteList);

        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(serviceLayer).removeBook(idCaptor.capture());

        serviceLayer.removeBook(1);

        verify(serviceLayer, times(1)).removeBook(idCaptor.getValue());
        assertEquals(1, idCaptor.getValue().intValue());


    }
}