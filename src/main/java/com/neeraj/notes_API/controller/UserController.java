package com.neeraj.notes_API.controller;

import com.neeraj.notes_API.model.User;
import com.neeraj.notes_API.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User savedUser = service.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping("/get/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        User user = service.findByUsername(authentication.getName());
        return ResponseEntity.ok(user);
    }
}

