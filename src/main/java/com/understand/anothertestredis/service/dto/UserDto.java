package com.understand.anothertestredis.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.understand.anothertestredis.entities.enums.MessageType;
import com.understand.anothertestredis.validation.annotation.UsernameValidation;
import lombok.*;
import org.mapstruct.EnumMapping;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @UsernameValidation
    private String username;
    @NotNull(message = "message type hasn't to be null")
    private MessageType type;
    @Min(18)
    @Max(80)
    private int age;
    @Email
    private String email;
    private List<SkillDto> skills;

}
