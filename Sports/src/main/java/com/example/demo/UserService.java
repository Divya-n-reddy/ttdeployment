package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Secret key to create admin
    private final String ADMIN_SECRET = "admin123"; // change this to something secure

    // ===============================
    // Register User
    // ===============================
    public User registerUser(User user, String adminSecret) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Check if admin secret matches
        if (adminSecret != null && adminSecret.equals(ADMIN_SECRET)) {
            user.setRole("ROLE_ADMIN");
        } else {
            user.setRole("ROLE_STUDENT");
        }

        return userRepository.save(user);
    }

    // ===============================
    // Login User
    // ===============================
    public User loginUser(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check encrypted password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    // ===============================
    // Get All Users
    // ===============================
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}