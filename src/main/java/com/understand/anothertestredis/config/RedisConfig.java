package com.understand.anothertestredis.config;

import com.understand.anothertestredis.entities.Message;
import com.understand.anothertestredis.entities.Skill;
import com.understand.anothertestredis.entities.User;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

//    @Bean
//    public JedisConnectionFactory connectionFactory() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        return new JedisConnectionFactory(configuration);
//    }
//
//    @Bean
//    public StringRedisSerializer stringRedisSerializer() {
//        return new StringRedisSerializer();
//    }
//
//    @Bean
//    public Jackson2JsonRedisSerializer<Object> jacksonJsonRedisJsonSerializer() {
//        return new Jackson2JsonRedisSerializer<>(Object.class);
//    }
//
//    @Bean
//    public RedisTemplate<?, ?> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory());
//        redisTemplate.setKeySerializer(stringRedisSerializer());
//        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());
//        return redisTemplate;
//    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    public Jackson2JsonRedisSerializer<User> jacksonJsonRedisJsonSerializer() {
        return new Jackson2JsonRedisSerializer<>(User.class);
    }

    @Bean(name = "user")
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());
        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<Message> jacksonJsonRedisJsonSerializer1() {
        return new Jackson2JsonRedisSerializer<>(Message.class);
    }

    @Bean(name = "message")
    public RedisTemplate<?, ?> redisTemplate1() {
        RedisTemplate<String, Message> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer1());
        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<Skill> jacksonJsonRedisJsonSerializer2() {
        return new Jackson2JsonRedisSerializer<>(Skill.class);
    }

    @Bean(name = "skill")
    public RedisTemplate<?, ?> redisTemplate2() {
        RedisTemplate<String, Skill> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer2());
        return redisTemplate;
    }



}
