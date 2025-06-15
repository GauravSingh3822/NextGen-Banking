package com.banking.backend.Banking_Backend.request;

import com.banking.backend.Banking_Backend.entites.Account;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegisterRequest {

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Phone is required")
        private String phone;

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password must be at least 6 characters long")
        private String password;

        @NotBlank(message = "Address is required") // Ensure the address is not blank
        private String address;
        @NotBlank(message = "DOB is required")
        private Date DOB;


    }

