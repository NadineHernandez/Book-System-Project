package com.company.notequeueconsumer.feign;

import com.company.notequeueconsumer.util.message.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="note-service")
public interface NoteFeignClient {

    @PostMapping("/notes")
    void createNote(@RequestBody Note note);

    @PutMapping("/notes")
    void updateNote(@RequestBody Note note);

}
