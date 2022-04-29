package com.understand.anothertestredis.repository;

import com.understand.anothertestredis.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository implements MyCrudOperations<User, String> {
    private static final String HASH_KEY_PREFIX = "UserKey:";

    @Autowired
    private RedisTemplate<String, Object> template;

    public User save(User entity) {
        template.opsForValue().set(HASH_KEY_PREFIX + entity.getUsername(), entity);
        return entity;
    }

    public User findByActualKey(String key) {
//        return template.opsForValue().get(key);
        return (User) template.opsForValue().get(key);
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        Set<String> keys = template.keys(HASH_KEY_PREFIX + "*");
        Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            userList.add(findByActualKey(it.next()));
        }

        return userList;
    }

    // key is username
    public User findByKey(String key) {
        key = HASH_KEY_PREFIX + key;
        return findByActualKey(key);
    }

}
