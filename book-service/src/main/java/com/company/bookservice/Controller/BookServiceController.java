package com.company.bookservice.Controller;

import com.company.bookservice.util.feign.NoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/books")
public class BookServiceController {

    @Autowired
    private final NoteClient client;

    BookServiceController(NoteClient client){
        this.client = client;
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getAllNotes(){
        return client.getNoteListfromDB();
    }

    @RequestMapping(value = "/notes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getNote(@PathVariable int id){
        return client.getNoteFromDB(id);
    }

    @GetMapping(value = "/notes/book/{book_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> getNotesByBook(@PathVariable("bookId") int bookId){
        return client.getNotesByBookfromDB(bookId);
    }
}
