package com.company.noteservice.dao;

import com.company.noteservice.dto.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteDaoJdbcTemplateImpl implements NoteDao{
    //Prepared statement strings
    private static final String INSERT_NOTE_SQL =
            "INSERT INTO NOTE (BOOK_ID, NOTE) VALUES (?,?)";

    private static final String SELECT_NOTE_SQL =
            "SELECT * from note where note_id = ?";

    private static final String SELECT_ALL_NOTES_SQL =
            "SELECT * from note";

    private static final String  DELETE_NOTE_SQL =
            "DELETE from note where note_id = ?";

    private static final String Update_NOTE_SQL =
            "Update note set book_id = ?, note =? where note_id = ?";

    private static final String SELECT_NOTES_BY_MAKE_SQL =
            "Select * from note where book_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NoteDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Note getNote(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_NOTE_SQL, this::mapRowToNote, id);
        } catch (EmptyResultDataAccessException e) {
            // If nothing is returned just catch the exception and return null;
            return null;
        }
    }

    @Override
    public List<Note> getAllNotes() {
        try{
            return jdbcTemplate.query(SELECT_ALL_NOTES_SQL, this::mapRowToNote);
        } catch (EmptyResultDataAccessException e) {
            // If nothing is returned just catch the exception and return null;
            return null;
        }
    }

    @Override
    @Transactional
    public Note addNote(Note note) {
        // Inserting data into the database.
        jdbcTemplate.update(INSERT_NOTE_SQL,
                note.getBookId(),
                note.getNote()
        );
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        //Updating java object with the newly created ID from the database.
        note.setNoteId(id);
        return note;
    }

    @Override
    public void updateNote(Note note) {
        jdbcTemplate.update(Update_NOTE_SQL,
                note.getBookId(),
                note.getNote(),
                note.getNoteId());
    }

    @Override
    public void deleteNote(int id) {
        jdbcTemplate.update(DELETE_NOTE_SQL, id);
    }

    @Override
    public List<Note> getNotesByBook(int bookId) {
        try{
            return jdbcTemplate.query(SELECT_NOTES_BY_MAKE_SQL, this::mapRowToNote, bookId);
        } catch (EmptyResultDataAccessException e) {
            // If nothing is returned just catch the exception and return null;
            return null;
        }
    }

    private Note mapRowToNote(ResultSet rs, int rowNum) throws SQLException {
        Note note = new Note();
        note.setNoteId(rs.getInt("note_id"));
        note.setBookId(rs.getInt("book_id"));
        note.setNote(rs.getString("note"));
        return note;
    }


}
