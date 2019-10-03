package com.company.bookservice.Controller;

import com.company.bookservice.util.feign.NoteClient;
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

    //Rabbit MQ Set up

    public static final String EXCHANGE = "queue-demo-exchange";
    public static final String ROUTING_KEY = "email.list.add.account.controller";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public BookServiceController(RabbitTemplate rabbitTemplate, NoteClient client) {
        this.rabbitTemplate = rabbitTemplate;
        this.client = client;
    }

    //EXAMPLE
//    @RequestMapping(value = "/account", method = RequestMethod.POST)
//    public String createAccount(@RequestBody Account account) {
//        // create message to send to email list creation queue
//        EmailListEntry msg = new EmailListEntry(account.getFirstName() + " " + account.getLastName(), account.getEmail());
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
//        System.out.println("Message Sent");
//
//        // Now do account creation stuff...
//
//        return "Account Created";
//    }


    //Feign Set up
    @Autowired
    private final NoteClient client;



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
