package com.understand.anothertestredis.service;

import com.understand.anothertestredis.repository.MessageRepository;
import com.understand.anothertestredis.repository.UserRepository;
import com.understand.anothertestredis.service.dto.MessageDto;
import com.understand.anothertestredis.service.dto.UserDto;
import com.understand.anothertestredis.service.mapper.MapMessageEntityDtoMapper;
import com.understand.anothertestredis.service.mapper.MapUserEntityDtoMapper;
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

    private final MapMessageEntityDtoMapper mapMessage;

    private final MapUserEntityDtoMapper mapUser;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository, MapMessageEntityDtoMapper mapMessage, MapUserEntityDtoMapper mapUser) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.mapMessage = mapMessage;
        this.mapUser = mapUser;
    }

    public MessageDto save(@Valid MessageDto dto) {
        UserDto byUsername = mapUser.entityToDto(userRepository.findByKey(dto.getUsername()));
        List<MessageDto> messages = byUsername.getMessages();
        messages.add(dto);
        byUsername.setMessages(messages);
        userRepository.save(mapUser.dtoToEntity(byUsername));
        return mapMessage.entityToDto(messageRepository.save(mapMessage.dtoToEntity(dto)));
    }

    public MessageDto findByKey(String key) {
        return mapMessage.entityToDto(messageRepository.findByActualKey(key));
    }

    public List<MessageDto> findAll() {
        return mapMessage.toDtoList(messageRepository.findAll());
    }

    public List<MessageDto> findUserAll(String username) {
        return mapMessage.toDtoList(messageRepository.findUserAll(username));
    }
}
