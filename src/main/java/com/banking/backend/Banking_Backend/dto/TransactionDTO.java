package com.banking.backend.Banking_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private String type;        // TRANSFER / DEPOSIT / WITHDRAW
    private Double amount;
    private String status;      // SUCCESS / FAILED / PENDING
    private String createdAt;   // Format: YYYY-MM-DD HH:mm:ss
    private Long fromAccountId;
    private Long toAccountId;
}
