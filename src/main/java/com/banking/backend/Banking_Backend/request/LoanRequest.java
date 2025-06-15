package com.banking.backend.Banking_Backend.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRequest {

    @NotBlank(message = "Loan type is required")
    private String loanType; // Home, Auto, Personal, etc.

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be positive")
    private Double amount;

    @NotNull(message = "Interest rate is required")
    @PositiveOrZero(message = "Interest rate must be 0 or more")
    private Double interestRate;

    @NotBlank(message = "Start date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date format must be YYYY-MM-DD")
    private String startDate;

    @NotBlank(message = "End date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date format must be YYYY-MM-DD")
    private String endDate;

    @NotNull(message = "User ID is required")
    private Long userId;
}
