package com.understand.anothertestredis.controller;

import com.understand.anothertestredis.entities.Message;
import com.understand.anothertestredis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    //add messages. If you mention the username what has already exists, the message is added for that user
    @PostMapping("/add")
    public ResponseEntity<Message> createOrAddMessage(@RequestBody Message messageEntity) {
        if (messageEntity.getContent() == null) {
            throw new ValidationException("Content have to be filled");
        }

        return ResponseEntity.ok(messageService.save(messageEntity));
    }

    //find message by key ex. like: http://www.localhost:8080/messages/MessageKey:Erika86f9d36-55b9-4c4b-8dc4-a036e74b90ae
    @GetMapping("/{key}")
    public ResponseEntity<Message> getMessageByKey(@PathVariable("key") String actualKey) {
        return ResponseEntity.ok(messageService.findByKey(actualKey));
    }

    //find all messages
    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }
}
