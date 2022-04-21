package com.understand.anothertestredis.repository;

import com.understand.anothertestredis.entities.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class MessageRepository implements MyCrudOperations<Message, String> {
    private static final String HASH_KEY_PREFIX = "MessageKey:";

    @Resource(name = "message")
    private RedisTemplate<String, Message> template;

    public Message save(Message entity) {
        template.opsForValue().set(HASH_KEY_PREFIX + entity.getId(), entity);
        return entity;
    }

    public Message findByKey(String key) {
        return template.opsForValue().get(key);
    }


    public List<Message> findAll() {
        List<Message> messageList = new ArrayList<>();

        Set<String> keys = template.keys(HASH_KEY_PREFIX + "*");
        Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            messageList.add(findByKey(it.next()));
        }
        return messageList;
    }

}
