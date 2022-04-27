package com.understand.anothertestredis.service.mapper;

import com.understand.anothertestredis.entities.User;
import com.understand.anothertestredis.service.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapUserEntityDtoMapper {
    UserDto entityToDto(User entity);
    User dtoToEntity(UserDto dto);
    List<UserDto> toDtoList(List<User> entityList);
}
