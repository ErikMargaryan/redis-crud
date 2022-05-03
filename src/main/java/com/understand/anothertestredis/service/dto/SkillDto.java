package com.understand.anothertestredis.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillDto {
    //username is not required to show
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "username has to been fulfilled")
    @NotNull(message = "username hasn't to be null")
    private String username;
    @NotBlank(message = "skill hasn't to be null")
    private String skill;
}
