package com.understand.anothertestredis.repository;

import com.understand.anothertestredis.entities.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class UserRepository implements MyCrudOperations<User> {
    private static final String HASH_KEY_PREFIX = "UserKey:";

    @Resource(name = "user")
    private RedisTemplate<String, User> template;

    public User save(User entity) {
        template.opsForValue().set(HASH_KEY_PREFIX + entity.getId(), entity);

        return entity;
    }

    public User findByKey(String key) {
        return template.opsForValue().get(key);
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        Set<String> keys = template.keys(HASH_KEY_PREFIX + "*");
        Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            userList.add(findByKey(it.next()));
        }

        return userList;
    }

    public User findByUsername(String username) {
        List<User> userEntityList = findAll();
        Optional<User> userOptional = userEntityList.stream().
                filter(userEntity1 -> userEntity1.getUsername().
                        equals(username)).findFirst();
        return userOptional.orElseGet(userOptional::get);
    }

}
