package com.understand.anothertestredis.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@RedisHash("message")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id = UUID.randomUUID().toString();
    //username is not required to show
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;
    @NotBlank(message = "message type hasn't to be null")
    private String content;
    private String localDateTime = LocalDateTime.now().toString();

}
