package com.banking.backend.Banking_Backend.services;

import com.banking.backend.Banking_Backend.dto.UserDTO;
import com.banking.backend.Banking_Backend.entites.User;
import com.banking.backend.Banking_Backend.request.ChangePasswordRequest;
import com.banking.backend.Banking_Backend.request.LoginRequest;
import com.banking.backend.Banking_Backend.request.RegisterRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO register(RegisterRequest request);
    String login(LoginRequest request);
    void changePassword(Long userId, ChangePasswordRequest request);
    UserDTO getUserById(Long id);


    UserDTO updateUser(Long userId, RegisterRequest request);

    void deleteUser(Long userId);

    List<UserDTO> getAllUsers();
}

