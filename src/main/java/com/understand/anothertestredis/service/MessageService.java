package com.understand.anothertestredis.service;

import com.understand.anothertestredis.repository.MessageRepository;
import com.understand.anothertestredis.repository.UserRepository;
import com.understand.anothertestredis.service.dto.MessageDto;
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

    private final MapMessageEntityDtoMapper mapMessage;

    @Autowired
    public MessageService(MessageRepository messageRepository, MapMessageEntityDtoMapper mapMessage) {
        this.messageRepository = messageRepository;
        this.mapMessage = mapMessage;
    }

    public MessageDto save(@Valid MessageDto dto) {
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

    public void delete(String key) {
        messageRepository.deleteUserSide(key);
    }
}
