package com.company.bookservice.Controller;

import com.company.bookservice.DTO.Book;
import com.company.bookservice.ViewModel.ViewModel;
import com.company.bookservice.service.ServiceLayer;
import com.company.bookservice.util.feign.NoteClient;
import com.company.bookservice.util.messages.Note;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/books")
public class BookServiceController {

    private ServiceLayer serviceLayer;

    public BookServiceController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    //note paths
    //c
    @PostMapping(value = "/notes")
    @ResponseStatus(HttpStatus.OK)
    public void addNote(@RequestBody Note note){
        serviceLayer.createNote(note);
    }
    //r
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Note getNote(@PathVariable int id){
        return serviceLayer.getNote(id);
    }
    //r all
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<Note> getAllNotes(){
        return serviceLayer.getAllNotes();
    }
    //r by book
    @GetMapping(value = "/notes/book/{book_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNotesByBook(@PathVariable int book_id){
        return serviceLayer.getNotesByBook(book_id);
    }
    //u
    @PutMapping(value = "/notes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNote(@RequestBody Note note){
        serviceLayer.updateNote(note);
    }
    //d
    @DeleteMapping(value = "/notes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNote(@PathVariable int id){
        serviceLayer.deleteNote(id);
    }

    //book paths

    //    public Book addBook(Book book);
    @PostMapping(value = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public ViewModel addBook(@RequestBody ViewModel viewModel){
        return serviceLayer.saveBook(viewModel);
    }

    //    public Book getBook(int bookId);
    @GetMapping(value = "/books/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ViewModel getBook(@PathVariable int id){
        return serviceLayer.findBook(id);
    }

    //    public List<Book> getAllBooks();
    @GetMapping(value = "/books")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ViewModel> getAllBooks(){
        List<ViewModel> books = serviceLayer.findAllBooks();
        return books;
    }

    //    public void updateBook(Book book);
    @PutMapping(value = "/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@RequestBody ViewModel viewModel){
        serviceLayer.updateBook(viewModel);
    }


    //    public void deleteBook(int bookId);
    @DeleteMapping(value = "/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable int id){
        serviceLayer.removeBook(id);
    }
}
