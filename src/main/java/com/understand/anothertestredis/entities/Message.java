package com.understand.anothertestredis.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("message")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    //username is not required to show
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "username has to been fulfilled")
    @NotNull(message = "username hasn't to be null")
    private String username;
    @NotBlank(message = "message type hasn't to be null")
    private String content;
    private String localDateTime = LocalDateTime.now().toString();

}
