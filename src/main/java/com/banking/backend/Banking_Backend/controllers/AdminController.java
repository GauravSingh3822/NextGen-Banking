package com.banking.backend.Banking_Backend.controllers;

import com.banking.backend.Banking_Backend.dto.UserDTO;
import com.banking.backend.Banking_Backend.request.RegisterRequest;
import com.banking.backend.Banking_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

//=============================================================================
    // 1. Register User (for admin to create a user)
    // 2. Update User Profile by ID (for admin to update user details)
    // 3. Delete User by ID (for admin to delete a user)
    // 4. Get User Details by ID (for admin to get user details)
    // 5. View All Users (for admin to view all users)
//============================================================================

    @Autowired
    private UserService userService;

    // 1. Register User (for admin to create a user)
    @PostMapping("/users/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterRequest request) {
        UserDTO createdUser = userService.register(request);
        return ResponseEntity.ok(createdUser);
    }

    // 2. Update User Profile by ID (for admin to update user details)
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateUserProfile(@PathVariable Long userId, @RequestBody RegisterRequest request) {
        UserDTO updatedUser = userService.updateUser(userId, request);
        return ResponseEntity.ok(updatedUser);
    }

    // 3. Delete User by ID (for admin to delete a user)
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // 4. Get User Details by ID (for admin to get user details)
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // 5. View All Users (for admin to view all users)
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
