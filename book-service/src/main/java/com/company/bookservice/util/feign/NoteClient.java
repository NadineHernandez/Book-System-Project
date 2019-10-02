package com.company.bookservice.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteClient {
    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    public List<Object> getNotesById(@PathVariable("book_id") int id);

    //and all other note paths
}
