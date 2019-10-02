package com.company.bookservice.DAO;

import com.company.bookservice.DTO.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    BookDao dao;

    @Before
    public void setUp() throws Exception {
        List<Book> books = dao.getAllBooks();
        books.stream()
                .forEach(book -> dao.deleteBook(book.getBookId()));
    }

    @Test
    public void addBook() {
        Book book = new Book("Sample Title", "Sample Author");
        book = dao.addBook(book);

        assertEquals(book, dao.getBook(book.getBookId()));
    }

    @Test
    public void getBook() {
        Book book = new Book("Sample Title", "Sample Author");
        book = dao.addBook(book);

        assertEquals(book, dao.getBook(book.getBookId()));
    }

    @Test
    public void getAllBooks() {
        Book book = new Book("Sample Title", "Sample Author");
        dao.addBook(book);
        Book book1 = new Book("Sample Title1", "Sample Author1");
        dao.addBook(book1);

        assertEquals(2, dao.getAllBooks().size());
    }

    @Test
    public void updateBook() {
        Book book = new Book("Sample Title", "Sample Author");
        book = dao.addBook(book);

        book.setTitle("Sample Title1");
        book.setAuthor("Sample Author1");
        dao.updateBook(book);

        assertEquals(book, dao.getBook(book.getBookId()));
    }

    @Test
    public void deleteBook() {Book book = new Book("Sample Title", "Sample Author");
        book = dao.addBook(book);
        dao.deleteBook(book.getBookId());

        assertNull(dao.getBook(book.getBookId()));
    }
}