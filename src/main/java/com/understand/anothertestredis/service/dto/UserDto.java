package com.understand.anothertestredis.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.understand.anothertestredis.entities.enums.MessageType;
import com.understand.anothertestredis.validation.annotation.UsernameValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
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
    private List<MessageDto> messages;

}
