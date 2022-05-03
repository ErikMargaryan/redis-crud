package com.understand.anothertestredis.service;

import com.understand.anothertestredis.entities.Skill;
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
    private final MapUserEntityDtoMapper mapUser;

    @Autowired
    public UserService(UserRepository userRepository, SkillRepository skillRepository, MapUserEntityDtoMapper mapUser) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
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

}


