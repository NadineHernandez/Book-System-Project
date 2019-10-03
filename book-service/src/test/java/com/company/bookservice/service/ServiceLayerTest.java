package com.company.bookservice.service;

import com.company.bookservice.DAO.BookDao;
import com.company.bookservice.DAO.BookImplemention;
import com.company.bookservice.DTO.Book;
import com.company.bookservice.ViewModel.ViewModel;
import com.company.bookservice.util.feign.NoteClient;
import com.company.bookservice.util.messages.Note;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    private ServiceLayer serviceLayer;
    private BookDao bookDao;
    private NoteClient client;

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

        ViewModel viewModel = new ViewModel();
        viewModel.setBookId(1);
        viewModel.setTitle("Sample Title");
        viewModel.setAuthor("Sample Author");
        viewModel.setNotes(client.getNotesByBookfromDB(viewModel.getBookId()));


        Book book2 = new Book();
        book2.setTitle("Sample Title");
        book2.setAuthor("Sample Author");

        List<ViewModel> bList = new ArrayList<>();
        bList.add(viewModel);

        doReturn(viewModel).when(bookDao).addBook(book2);
        doReturn(viewModel).when(bookDao).getBook(1);
        doReturn(bList).when(bookDao).getAllBooks();
    }

    @Before
    public void setUp() throws Exception {
        setUpBookDaoMock();
        serviceLayer = new ServiceLayer(bookDao, client);
    }

    @Test
    public void saveBook() {
        ViewModel viewModel = new ViewModel();
    }

    @Test
    public void findBook() {
    }

    @Test
    public void findAllBooks() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void removeBook() {
    }
}