package com.understand.anothertestredis.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.understand.anothertestredis.entities.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@RedisHash("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id = UUID.randomUUID().toString();
    @NotNull(message = "message type hasn't to be null")
    private MessageType type;
    @NotBlank(message = "username has to been fulfilled")
    @NotNull(message = "username hasn't to be null")
    //    @UsernameValidation
    private String username;
    @Min(18)
    @Max(80)
    private int age;
    @Email
    private String email;
    private List<Message> messages;

}
