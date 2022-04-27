package com.understand.anothertestredis.controller;

import com.understand.anothertestredis.service.UserService;
import com.understand.anothertestredis.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserDto> getByUsername(@RequestParam(value="username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    //show all users with their messages
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

}
