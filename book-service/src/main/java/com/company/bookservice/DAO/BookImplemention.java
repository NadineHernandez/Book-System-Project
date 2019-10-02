package com.company.bookservice.DAO;

import com.company.bookservice.DTO.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookImplemention implements BookDao {
    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public Book getBook(int bookId) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(int bookId) {

    }
}
