package com.understand.anothertestredis.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.understand.anothertestredis.validation.annotation.UsernameExistValidation;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {

    //username is not required to show
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "username has to been fulfilled")
    @NotNull(message = "username hasn't to be null")
    @UsernameExistValidation
    private String username;
    @NotBlank(message = "message type hasn't to be null")
    private String content;
    private String localDateTime = LocalDateTime.now().toString();

}
