package com.understand.anothertestredis.controller;

import com.understand.anothertestredis.service.MessageService;
import com.understand.anothertestredis.service.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MessageDto> createOrAddMessage(@RequestBody MessageDto messageDto) {

        return ResponseEntity.ok(messageService.save(messageDto));
    }

    //find message by key ex. like: http://www.localhost:8080/messages/MessageKey:Erika86f9d36-55b9-4c4b-8dc4-a036e74b90ae
    @GetMapping("/{key}")
    public ResponseEntity<MessageDto> getMessageByKey(@PathVariable("key") String actualKey) {
        return ResponseEntity.ok(messageService.findByKey(actualKey));
    }

    //find all messages by username
    @GetMapping("/all/{username}")
    public ResponseEntity<List<MessageDto>> getAllMessages(@PathVariable String username) {
        return ResponseEntity.ok(messageService.findUserAll(username));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MessageDto>> getAll() {
        return ResponseEntity.ok(messageService.findAll());
    }
}
