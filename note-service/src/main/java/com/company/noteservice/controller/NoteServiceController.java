package com.company.noteservice.controller;


import com.company.noteservice.dao.NoteDao;
import com.company.noteservice.dto.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("notes") // http://localhost:8080/note
public class NoteServiceController {
    private NoteDao noteDao;

    @Autowired
    public NoteServiceController(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note addNoteToDB(@RequestBody @Valid Note note) {
        return noteDao.addNote(note);
    }

    @DeleteMapping(path = "/{noteId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteNoteFromDB(@PathVariable int noteId) {
        noteDao.deleteNote(noteId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getNoteListfromDB() {
        return noteDao.getAllNotes();

    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Note getNoteFromDB(@PathVariable int id) throws Exception {
        Note note = noteDao.getNote(id);
        if (note == null) {
            throw new Exception("Note not found.");
        }
        return note;

    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateNoteInDB(@RequestBody @Valid Note note) {
        noteDao.updateNote(note);

    }

    @GetMapping(path = "book/{bookId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getNotesByManufacturerfromDB(@PathVariable int bookId) throws Exception {
        List<Note> gList = noteDao.getNotesByBook(bookId);
        if (gList.size() == 0) {
            throw new Exception("Book not found.");
        }
        return gList;
    }




}
