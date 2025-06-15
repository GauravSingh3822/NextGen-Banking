package com.banking.backend.Banking_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

    private Long id;
    private String cardNumber;         // Masked or full
    private String cardType;           // e.g., Debit, Credit
    private Date issueDate;          // Format: YYYY-MM-DD
    private Date expiryDate;         // Format: YYYY-MM-DD
    private String cardStatus;         // e.g., Active, Blocked, Expired
    private Long userId;               // Card holder's user ID
    private boolean blocked;
}
