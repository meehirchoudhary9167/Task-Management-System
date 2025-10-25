package com.todo.todo.Backend.service;


import com.todo.todo.Backend.model.User;
import com.todo.todo.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username is already taken!";
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email is already registered!";
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return "Login successful!";
            } else {
                return "Invalid password!";
            }
        } else {
            return "User not found!";
        }
    }
}

