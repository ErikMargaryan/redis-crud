package com.understand.anothertestredis.service.mapper;

import com.understand.anothertestredis.entities.Message;
import com.understand.anothertestredis.service.dto.MessageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapMessageEntityDtoMapper {
    MessageDto entityToDto(Message entity);
    Message dtoToEntity(MessageDto dto);
    List<MessageDto> toDtoList(List<Message> entityList);
}
