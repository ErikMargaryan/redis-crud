package com.understand.anothertestredis.controller;

import com.understand.anothertestredis.entities.Message;
import com.understand.anothertestredis.service.MessageService;
import com.understand.anothertestredis.service.dto.MessageDto;
import com.understand.anothertestredis.service.mapper.MapMessageEntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<MessageDto> createOrAddMessage(@RequestBody MessageDto messageDto) {
        if (messageDto.getContent() == null) {
            throw new ValidationException("Content have to be filled");
        }

        return ResponseEntity.ok(messageService.save(messageDto));
    }

    //find message by key ex. like: http://www.localhost:8080/messages/MessageKey:Erika86f9d36-55b9-4c4b-8dc4-a036e74b90ae
    @GetMapping("/{key}")
    public ResponseEntity<MessageDto> getMessageByKey(@PathVariable("key") String actualKey) {
        return ResponseEntity.ok(messageService.findByKey(actualKey));
    }

    //find all messages
    @GetMapping("/all")
    public ResponseEntity<List<MessageDto>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }
}
