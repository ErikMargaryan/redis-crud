package com.understand.anothertestredis.controller;

import com.understand.anothertestredis.entities.User;
import com.understand.anothertestredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
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
    public ResponseEntity<User> createUser(@RequestBody @Valid User userEntity) {
//        userService.findAll().forEach(userEntity1 -> {
//            if (userEntity1.getUsername().equals(userEntity.getUsername())) {
//                throw new DuplicateKeyException("This username have already been");
//            }
//        });
        return new ResponseEntity<>(userService.save(userEntity), HttpStatus.CREATED);
    }

    //find user by key
    @GetMapping("/{key}")
    public ResponseEntity<User> getUserMyKey(@PathVariable("key") String actualKey) {
        return ResponseEntity.ok(userService.findByKey(actualKey));
    }

    //show "username"'s messages and details
    @GetMapping
    public ResponseEntity<User> getByUsername(@RequestParam(value="username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    //show all users with their messages
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

}
