package com.company.bookservice.service;

import com.company.bookservice.DAO.BookDao;
import com.company.bookservice.DAO.BookImplemention;
import com.company.bookservice.DTO.Book;
import com.company.bookservice.ViewModel.ViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    private ServiceLayer serviceLayer;
    private BookDao bookDao;

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
        serviceLayer = new ServiceLayer(bookDao);
    }

    @Test
    public void saveBook() {
        ViewModel viewModel = new ViewModel();
        viewModel.setB
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