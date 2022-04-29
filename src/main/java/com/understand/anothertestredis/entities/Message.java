package com.understand.anothertestredis.entities;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("message")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String content;
    private String localDateTime = LocalDateTime.now().toString();

}
