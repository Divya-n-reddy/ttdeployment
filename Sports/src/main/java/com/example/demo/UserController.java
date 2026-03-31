package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // ==========================
    // Register (Student or Admin)
    // ==========================
    @PostMapping("/register")
    public User register(
            @RequestBody User user,
            @RequestParam(required = false) String adminSecret) {

        return userService.registerUser(user, adminSecret);
    }

    // ==========================
    // Login
    // ==========================
    @PostMapping("/login")
    public User login(@RequestBody User user) {

        return userService.loginUser(
                user.getUsername(),
                user.getPassword()
        );
    }

    // ==========================
    // Get All Users
    // ==========================
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}