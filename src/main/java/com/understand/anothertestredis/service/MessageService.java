package com.understand.anothertestredis.service;

import com.understand.anothertestredis.entities.Message;
import com.understand.anothertestredis.entities.User;
import com.understand.anothertestredis.repository.MessageRepository;
import com.understand.anothertestredis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class MessageService {
    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Message save(@Valid Message entity) {
        User byUsername = userRepository.findByUsername(entity.getUsername());
        List<Message> messages = byUsername.getMessages();
        messages.add(entity);
        byUsername.setMessages(messages);
        userRepository.save(byUsername);
        return messageRepository.save(entity);
    }

    public Message findByKey(String key) {
        return messageRepository.findByKey(key);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
