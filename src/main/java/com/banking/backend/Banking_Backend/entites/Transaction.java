package com.banking.backend.Banking_Backend.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @NotNull(message = "Account reference is required")
    private Account account;

    @Column(name = "amount")
    @Positive(message = "Transaction amount must be greater than 0")
    private double amount;

    @Column(name = "description")
    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 100, message = "Description must be between 3 to 100 characters")
    private String description;

    @Column(name = "type")
    @NotBlank(message = "Transaction type is required")
    @Pattern(regexp = "DEPOSIT|WITHDRAWAL|TRANSFER", message = "Transaction type must be DEPOSIT, WITHDRAWAL, or TRANSFER")
    private String type;

    @Column(name = "balance")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative")
    private double balance;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Timestamp is required")
    private Date timestamp;

    @Pattern(regexp = "PENDING|COMPLETED|FAILED", message = "Status must be PENDING, COMPLETED, or FAILED")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;


    private Long fromAccountId;

    private Long toAccountId;
}
