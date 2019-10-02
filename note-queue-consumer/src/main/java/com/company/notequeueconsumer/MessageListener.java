package com.company.notequeueconsumer;

import com.company.notequeueconsumer.feign.NoteFeignClient;
import com.company.notequeueconsumer.util.message.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    NoteFeignClient feignClient;

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Note msg) {

        if (msg.getNoteId() == 0) {
            feignClient.createNote(msg);
        } else {
            feignClient.updateNote(msg);
        }
    }
}
