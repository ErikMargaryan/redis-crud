package com.understand.anothertestredis.service.mapper;

import com.understand.anothertestredis.entities.Skill;
import com.understand.anothertestredis.service.dto.SkillDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapSkillEntityDtoMapper {

    SkillDto entityToDto(Skill entity);

    Skill dtoToEntity(SkillDto dto);

    List<SkillDto> toDtoList(List<Skill> entityList);

}
