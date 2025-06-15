package com.banking.backend.Banking_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;     // USER / ADMIN

    public UserDTO(Long userId, String name, String email, String phoneNumber) {
    }
}
