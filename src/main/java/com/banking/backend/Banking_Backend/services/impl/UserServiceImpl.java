package com.banking.backend.Banking_Backend.services.impl;

import com.banking.backend.Banking_Backend.dto.UserDTO;
import com.banking.backend.Banking_Backend.entites.Role;
import com.banking.backend.Banking_Backend.entites.User;
import com.banking.backend.Banking_Backend.repository.UserRepository;
import com.banking.backend.Banking_Backend.request.ChangePasswordRequest;
import com.banking.backend.Banking_Backend.request.LoginRequest;
import com.banking.backend.Banking_Backend.request.RegisterRequest;
import com.banking.backend.Banking_Backend.services.UserService;
import com.banking.backend.Banking_Backend.exception.UserAlreadyExistsException;
import com.banking.backend.Banking_Backend.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(RegisterRequest request) {
        // Check if the user already exists with the provided email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with this email.");
        }

        // Create the user entity from the request data
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt password
        user.setAddress(request.getAddress()); // Set the address from the request
        user.setRole(Role.USER); // Set the default role as USER

        // Save the user and return the saved user DTO
        User savedUser = userRepository.save(user);
        return toDTO(savedUser);
    }



    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("No user found with this email."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) { // Verify password
            throw new RuntimeException("Invalid password");
        }

        return "Login successful"; // Replace with token generation if using JWT
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) { // Verify old password
            throw new RuntimeException("Incorrect old password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword())); // Encrypt new password
        userRepository.save(user);
    }
    // Method to update user profile
    @Transactional
    public UserDTO updateUser(Long userId, RegisterRequest request) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(request.getName());
        existingUser.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt password before saving
        existingUser.setEmail(request.getEmail());

        User updatedUser = userRepository.save(existingUser);
        return new UserDTO(updatedUser.getUserId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getPhoneNumber());
    }

    // Method to delete user by ID
    @Transactional
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(existingUser);
    }


    @Override
    public List<UserDTO> getAllUsers() {
        // Fetch all users and convert them to UserDTO
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }




    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return toDTO(user);
    }

    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhoneNumber());
        dto.setRole(user.getRole().name()); // Convert enum to string
        return dto;
    }
}
