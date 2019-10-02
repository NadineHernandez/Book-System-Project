package com.company.noteservice.dao;

import com.company.noteservice.dto.Note;

import java.util.List;

public interface NoteDao {


    Note getNote(int id);

    /**
     * Get all notes
     * @return
     */
    List<Note> getAllNotes();

    /**
     * Adds the given Note to the DB
     * @param note
     * @return
     */
    Note addNote(Note note);

    /**
     * Updates the Note with the matching id of the given Note object
     * @param note
     */
    void updateNote(Note note);

    /**
     * Delete the Note with the given id.
     * @param id
     */
    void deleteNote(int id);

    List<Note> getNotesByBook(int bookId);

}
