package com.understand.anothertestredis.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("skill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String skill;

}
