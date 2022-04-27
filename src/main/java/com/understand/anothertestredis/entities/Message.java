package com.understand.anothertestredis.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String content;
    private String localDateTime = LocalDateTime.now().toString();

}
