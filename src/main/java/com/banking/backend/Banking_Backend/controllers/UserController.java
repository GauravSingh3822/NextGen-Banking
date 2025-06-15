package com.banking.backend.Banking_Backend.controllers;

import com.banking.backend.Banking_Backend.dto.UserDTO;
import com.banking.backend.Banking_Backend.request.ChangePasswordRequest;
import com.banking.backend.Banking_Backend.request.LoginRequest;
import com.banking.backend.Banking_Backend.request.RegisterRequest;
import com.banking.backend.Banking_Backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

//===============================================================
    // 1. Register User
    // 2. Login User
    // 3. Logout User
    // 4. Update User Profile by id
    // 5. Delete User by id
    // 6. Get User Details by id
    // 3. Logout User
//===============================================================
    @Autowired
    private UserService userService;

    // 1. Register User
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid RegisterRequest request){
        return ResponseEntity.ok(userService.register(request));
    }
    // 2. Login User
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
    // Update Password
    @PostMapping("/{userId}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Long userId, @RequestBody @Valid ChangePasswordRequest request){
        userService.changePassword(userId, request);
        return ResponseEntity.ok("Password updated Succesfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Assuming JWT token invalidation or session clear
        return ResponseEntity.ok("User logged out successfully");
    }

    // 4. Update User Profile
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody @Valid RegisterRequest request){
        UserDTO updatedUser = userService.updateUser(userId, request);
        return ResponseEntity.ok(updatedUser);
    }

    // 5. Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // 6. Get User Details
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }


}
