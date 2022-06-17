package com.understand.anothertestredis.controller;

import com.understand.anothertestredis.service.UserService;
import com.understand.anothertestredis.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //creating user with checking have already exists or not: if yes then throws an exception
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    //show "username"'s messages and details
    @GetMapping
    public ResponseEntity<UserDto> getByUsername(@RequestParam(value = "username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    //show all users with their messages
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String key, @RequestBody UserDto user) {
        UserDto dto = userService.updateUser(key, user);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{key}")
    public void deleteUser(@PathVariable("key") String key) {
        userService.delete(key);
    }
}
