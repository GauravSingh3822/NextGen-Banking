package com.banking.backend.Banking_Backend.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    @NotBlank(message = "Account type is required")
    @Pattern(regexp = "^(Savings|Current|Fixed|Recurring)$")
    private String accountType;

    @NotNull(message = "Initial deposit is required")
    @PositiveOrZero(message = "Deposit must be positive or zero")
    private Double initialDeposit;
}
