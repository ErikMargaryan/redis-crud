package com.understand.anothertestredis.service;

import com.understand.anothertestredis.repository.SkillRepository;
import com.understand.anothertestredis.repository.UserRepository;
import com.understand.anothertestredis.service.dto.SkillDto;
import com.understand.anothertestredis.service.dto.UserDto;
import com.understand.anothertestredis.service.mapper.MapSkillEntityDtoMapper;
import com.understand.anothertestredis.service.mapper.MapUserEntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class SkillService {
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;
    private final MapUserEntityDtoMapper mapUser;
    private final MapSkillEntityDtoMapper mapSkill;

    @Autowired
    public SkillService(SkillRepository skillRepository, UserRepository userRepository, MapUserEntityDtoMapper mapUser, MapSkillEntityDtoMapper mapSkill) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
        this.mapUser = mapUser;
        this.mapSkill = mapSkill;
    }

    public SkillDto save(@Valid SkillDto dto) {
        UserDto byUsername = mapUser.entityToDto(userRepository.findByKey(dto.getUsername()));
        List<SkillDto> skills = byUsername.getSkills();
        skills.add(dto);
        byUsername.setSkills(skills);
        userRepository.save(mapUser.dtoToEntity(byUsername));
        return mapSkill.entityToDto(skillRepository.save(mapSkill.dtoToEntity(dto)));
    }

    public SkillDto findByKey(String key) {
        return mapSkill.entityToDto(skillRepository.findByActualKey(key));
    }

    public List<SkillDto> findAll() {
        return mapSkill.toDtoList(skillRepository.findAll());
    }

    public List<SkillDto> findUserAll(String username) {
        return mapSkill.toDtoList(skillRepository.findUserAll(username));
    }

    public void delete(String key) {
        skillRepository.deleteUserSide(key);
    }
}
