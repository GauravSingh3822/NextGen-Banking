package com.banking.backend.Banking_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private String accountNumber;
    private String accountType;  // SAVINGS / CURRENT / etc.
    private Double balance;
    private Long userId;         // Refers to the owner of the account
}
