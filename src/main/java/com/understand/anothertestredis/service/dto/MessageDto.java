package com.understand.anothertestredis.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {

    //username is not required to show
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "username has to been fulfilled")
    @NotNull(message = "username hasn't to be null")
    private String username;
    @NotBlank(message = "message type hasn't to be null")
    private String content;
    private String localDateTime = LocalDateTime.now().toString();

}
