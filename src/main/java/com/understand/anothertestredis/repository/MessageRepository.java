package com.understand.anothertestredis.repository;

import com.understand.anothertestredis.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MessageRepository implements MyCrudOperations<Message, String> {
    private static final String HASH_KEY_PREFIX = "MessageKey:";

    @Autowired
    private RedisTemplate<String, Object> template;

    public Message save(Message entity) {
        template.opsForValue().set(HASH_KEY_PREFIX + entity.getUsername() + UUID.randomUUID(), entity);
        return entity;
    }

    public Message findByActualKey(String key) {
        return (Message) template.opsForValue().get(key);
    }


    public List<Message> findAll() {
        List<Message> messageList = new ArrayList<>();

        Set<String> keys = template.keys(HASH_KEY_PREFIX + "*");
        Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            messageList.add(findByActualKey(it.next()));
        }
        return messageList;
    }

    public List<Message> findUserAll(String username) {
        List<Message> messageList = new ArrayList<>();

        Set<String> keys = template.keys(HASH_KEY_PREFIX + username + "*");
        Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            messageList.add(findByActualKey(it.next()));
        }
        return messageList;
    }

}
