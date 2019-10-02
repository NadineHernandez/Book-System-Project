package com.company.bookservice.DAO;

import com.company.bookservice.DTO.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookImplemention implements BookDao {

    //prepared statements

    private static final String INSERT_BOOK_SQL = "INSERT INTO book (title, author) VALUE (?,?)";
    private static final String SELECT_BOOK_SQL = "SELECT * FROM book WHERE book_id = ?";
    private static final String SELECT_ALL_BOOKS_SQL = "SELECT * FROM book";
    private static final String UPDATE_BOOK_SQL = "UPDATE book SET title = ?, author = ? WHERE book_id = ?";
    private static final String DELETE_BOOK_SQL = "DELETE FROM book WHERE book_id = ?";

    private JdbcTemplate jdbcTemplate;

    public BookImplemention(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book addBook(Book book) {
        jdbcTemplate.update(INSERT_BOOK_SQL, book.getTitle(), book.getAuthor());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        book.setBookId(id);
        return book;
    }

    @Override
    public Book getBook(int bookId) {
        try{
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, bookId);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOKS_SQL, this::mapRowToBook);
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK_SQL,book.getTitle(),book.getAuthor(),book.getBookId());
    }

    @Override
    public void deleteBook(int bookId) {
        jdbcTemplate.update(DELETE_BOOK_SQL, bookId);
    }

    //row mapper
    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        return book;
    }
}
