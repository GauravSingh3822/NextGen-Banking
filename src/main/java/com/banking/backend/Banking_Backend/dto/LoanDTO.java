package com.banking.backend.Banking_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {

    private Long id;
    private String loanType;           // e.g., Home, Personal, Auto
    private Double amount;
    private Double interestRate;
    private String startDate;          // Format: YYYY-MM-DD
    private String endDate;            // Format: YYYY-MM-DD
    private String status;             // e.g., Approved, Pending, Rejected
    private Long userId;               // Refers to the borrower (User)
}
