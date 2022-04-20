package com.understand.anothertestredis.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.understand.anothertestredis.entities.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @Id
    private String id = UUID.randomUUID().toString();
    private MessageType type;
    private String username;
    @Min(18)
    @Max(80)
    private int age;
    @Email
    private String email;
    private List<Message> messages;

}
