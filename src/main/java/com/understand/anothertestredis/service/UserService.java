package com.understand.anothertestredis.service;

import com.understand.anothertestredis.entities.Skill;
import com.understand.anothertestredis.entities.User;
import com.understand.anothertestredis.repository.MessageRepository;
import com.understand.anothertestredis.repository.SkillRepository;
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
    private final SkillRepository skillRepository;
    private final MessageRepository messageRepository;
    private final MapUserEntityDtoMapper mapUser;

    @Autowired
    public UserService(UserRepository userRepository, SkillRepository skillRepository, MessageRepository messageRepository, MapUserEntityDtoMapper mapUser) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.messageRepository = messageRepository;
        this.mapUser = mapUser;
    }

    public UserDto save(@Valid UserDto dto) {
        dto.getSkills().forEach(skillDto -> {
            Skill skill = new Skill();
            skill.setUsername(dto.getUsername() + UUID.randomUUID());
            skill.setSkill(skillDto.getSkill());
            skillRepository.save(skill);
        });
        return mapUser.entityToDto(userRepository.save(mapUser.dtoToEntity(dto)));
    }

    public UserDto findByUsername(String key) {
        return mapUser.entityToDto(userRepository.findByKey(key));
    }

    public List<UserDto> findAll() {
        return mapUser.toDtoList(userRepository.findAll());
    }

    public void delete(String key) {
        userRepository.delete(key);
        skillRepository.deleteUserSide(key);
        messageRepository.deleteUserSide(key);
    }

    public UserDto updateUser(String key, @Valid UserDto dto) {
        User entity = mapUser.dtoToEntity(findByUsername(key));
        entity.setUsername(key);
        entity.setType(dto.getType());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        //skilleri pahov ligikan mi qich urish
        return save(mapUser.entityToDto(entity));
    }
}


