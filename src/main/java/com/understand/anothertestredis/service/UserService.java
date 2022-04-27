package com.understand.anothertestredis.service;

import com.understand.anothertestredis.entities.Message;
import com.understand.anothertestredis.entities.User;
import com.understand.anothertestredis.repository.MessageRepository;
import com.understand.anothertestredis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;

@Service
@Validated
public class UserService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public UserService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public User save(@Valid User entity) {
        Message message = new Message();
        entity.getMessages().forEach(messageEntity -> {
            message.setUsername(entity.getUsername() + UUID.randomUUID());
            message.setContent(messageEntity.getContent());
            message.setLocalDateTime(messageEntity.getLocalDateTime());
            messageRepository.save(message);
        });
        return userRepository.save(entity);
    }

    public User findByUsername(String key) {
        return userRepository.findByKey(key);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}


