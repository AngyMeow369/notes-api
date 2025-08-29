package com.neeraj.notes_API.service;

import com.neeraj.notes_API.model.User;
import com.neeraj.notes_API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder; // inject the encoder

    public User createUser(User user) {
        // hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}

