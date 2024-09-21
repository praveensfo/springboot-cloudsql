package com.spring.implementation.controller;

import com.spring.implementation.model.Users;
import com.spring.implementation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    @GetMapping
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Optional<Users> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new user
    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userRepository.save(user);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users updatedUser) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setName(updatedUser.getName());
            user.setRole(updatedUser.getRole());
            return ResponseEntity.ok(userRepository.save(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
