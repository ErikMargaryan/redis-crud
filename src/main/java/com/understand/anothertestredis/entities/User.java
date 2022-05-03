package com.understand.anothertestredis.entities;

import com.understand.anothertestredis.entities.enums.MessageType;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@RedisHash("user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private MessageType type;
    private int age;
    private String email;
    private List<Skill> skills;

}
