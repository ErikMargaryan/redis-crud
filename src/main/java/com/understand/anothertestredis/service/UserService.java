package com.understand.anothertestredis.service;

import com.understand.anothertestredis.entities.Message;
import com.understand.anothertestredis.repository.MessageRepository;
import com.understand.anothertestredis.repository.UserRepository;
import com.understand.anothertestredis.service.dto.UserDto;
import com.understand.anothertestredis.service.mapper.MapUserEntityDtoMapper;
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
    private final MapUserEntityDtoMapper mapUser;

    @Autowired
    public UserService(UserRepository userRepository, MessageRepository messageRepository, MapUserEntityDtoMapper mapUser) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.mapUser = mapUser;
    }

    public UserDto save(@Valid UserDto dto) {


        dto.getMessages().forEach(messageEntity -> {
            Message message = new Message();
            message.setUsername(dto.getUsername() + UUID.randomUUID());
            message.setContent(messageEntity.getContent());
            message.setLocalDateTime(messageEntity.getLocalDateTime());
            messageRepository.save(message);
        });
        return mapUser.entityToDto(userRepository.save(mapUser.dtoToEntity(dto)));
    }

    public UserDto findByUsername(String key) {
        return mapUser.entityToDto(userRepository.findByKey(key));
    }

    public List<UserDto> findAll() {
        return mapUser.toDtoList(userRepository.findAll());
    }

}


