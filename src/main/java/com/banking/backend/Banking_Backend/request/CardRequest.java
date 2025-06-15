package com.banking.backend.Banking_Backend.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CardRequest {

    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    @NotBlank(message = "Card type is required")
    @Pattern(regexp = "^(Credit|Debit)$", message = "Card type must be Credit or Debit")
    private String cardType;

    @NotBlank(message = "Issue date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private Date issueDate;

    @NotBlank(message = "Expiry date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private Date expiryDate;

    @NotNull(message = "User ID is required")
    private Long userId;


    }

