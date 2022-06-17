package com.understand.anothertestredis.controller;

import com.understand.anothertestredis.service.SkillService;
import com.understand.anothertestredis.service.dto.SkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("skills")
public class SkillController {
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    //add skills. If you mention the username what has already exists, the skill is added for that user
    @PostMapping("/add")
    public ResponseEntity<SkillDto> createOrAddMessage(@RequestBody SkillDto messageDto) {
        if (messageDto.getSkill() == null) {
            throw new ValidationException("Skill have to be filled");
        }

        return ResponseEntity.ok(skillService.save(messageDto));
    }

    //find skill by key ex. like: http://www.localhost:8080/skills/SkillKey:Springa86f9d36-55b9-4c4b-8dc4-a036e74b90ae
    @GetMapping("/{key}")
    public ResponseEntity<SkillDto> getMessageByKey(@PathVariable("key") String actualKey) {
        return ResponseEntity.ok(skillService.findByKey(actualKey));
    }

    //find all skills by username
    @GetMapping("/all/{username}")
    public ResponseEntity<List<SkillDto>> getAllMessages(@PathVariable String username) {
        return ResponseEntity.ok(skillService.findUserAll(username));
    }

    //find all skills
    @GetMapping("/all")
    public ResponseEntity<List<SkillDto>> getAll() {
        return ResponseEntity.ok(skillService.findAll());
    }

    //delete that user's all skills
    @DeleteMapping("/{key}")
    public void deleteByUsername(@PathVariable("key") String key) {
        skillService.delete(key);
    }
}
