package com.understand.anothertestredis.repository;

import com.understand.anothertestredis.entities.Skill;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class SkillRepository implements MyCrudOperations<Skill, String> {

    private static final String HASH_KEY_PREFIX = "SkillKey:";

    @Resource(name = "skill")
    private RedisTemplate<String, Skill> template;

    @Override
    public Skill save(Skill skill) {
        template.opsForValue().set(HASH_KEY_PREFIX + skill.getUsername() + UUID.randomUUID(), skill);
        return skill;
    }

    @Override
    public Skill findByActualKey(String key) {
        return template.opsForValue().get(key);
    }

    @Override
    public List<Skill> findAll() {
        List<Skill> skillList = new ArrayList<>();
        Set<String> keys = template.keys(HASH_KEY_PREFIX + "*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            skillList.add(findByActualKey(it.next()));
        }
        return skillList;
    }

    public List<Skill> findUserAll(String username) {
        List<Skill> skillList = new ArrayList<>();
        Set<String> keys = template.keys(HASH_KEY_PREFIX + username + "*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            skillList.add(findByActualKey(it.next()));
        }
        return skillList;
    }
}
