package com.banking.backend.Banking_Backend.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_card")
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long Id;

    @Column(name = "card_number", unique = true)
    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    @Column(name = "card_type")
    @NotBlank(message = "Card type is required")
    @Pattern(regexp = "CREDIT|DEBIT", message = "Card type must be CREDIT or DEBIT")
    private String cardType;

    @Column(name = "card_holder_name")
    @NotBlank(message = "Card holder name is required")
    @Size(min = 2, max = 100, message = "Card holder name must be between 2 and 100 characters")
    private String cardHolderName;

    @Column(name = "issue_date")
    @NotNull(message = "Issue date is required")
    @Future(message = "Issue date must be in the future")
    private Date issueDate;

    @Column(name = "expiry_date")
    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private Date expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private boolean blocked;





}
