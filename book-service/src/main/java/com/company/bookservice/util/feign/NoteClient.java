package com.company.bookservice.util.feign;

import com.company.bookservice.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-service")
@RequestMapping("notes")
public interface NoteClient {
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Note addNoteToDB(@RequestBody Object note);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteNoteFromDB(@PathVariable int note_id);

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getNoteListfromDB();

    @GetMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Note getNoteFromDB(@PathVariable int id);

//    @PutMapping
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void updateNoteInDB(@RequestBody Note note);

    @GetMapping(path = "book/{book_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Note> getNotesByBookfromDB(@PathVariable int bookId);
}
