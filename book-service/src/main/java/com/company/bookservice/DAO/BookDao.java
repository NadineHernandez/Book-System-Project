package com.company.bookservice.DAO;

import com.company.bookservice.DTO.Book;

import java.util.List;

public interface BookDao {

    public Book addBook(Book book);

    public Book getBook(int bookId);

    public List<Book> getAllBooks();

    public void updateBook(Book book);

    public void deleteBook(int bookId);

}
