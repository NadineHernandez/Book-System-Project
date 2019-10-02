package com.company.bookservice.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-service")
@RequestMapping("notes")
public interface NoteClient {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object addNoteToDB(@RequestBody Object note);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteNoteFromDB(@PathVariable int note_id);

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Object> getNoteListfromDB();

    @GetMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Object getNoteFromDB(@PathVariable int id);

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateNoteInDB(@RequestBody Object note);

    @GetMapping(path = "book/{book_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Object> getNotesByBookfromDB(@PathVariable int bookId);
}
