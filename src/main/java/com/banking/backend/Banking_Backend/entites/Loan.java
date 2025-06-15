package com.banking.backend.Banking_Backend.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    @Column(name = "loan_type")
    @NotBlank(message = "Loan type is required")
    @Size(min = 3, max = 30, message = "Loan type must be between 3 to 30 characters")
    private String loanType;

    @Column(name = "loan_amount")
    @Positive(message = "Loan amount must be a positive value")
    private double amount;

    @Column(name = "interest_rate")
    @DecimalMin(value = "0.0", inclusive = false, message = "Interest rate must be greater than 0")
    private double interestRate;

    @Column(name = "monthly_payment")
    @DecimalMin(value = "0.0", inclusive = false, message = "Monthly payment must be greater than 0")
    private double monthlyPayment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 50, message = "Status must be less than 50 characters")
    private String status;
}
