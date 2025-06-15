package com.banking.backend.Banking_Backend.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {


        @Id
        private long id;

        @PositiveOrZero(message = "Account balance must be zero or positive")
        private double accountBalance;

        @NotBlank(message = "Account type is required")
        @Pattern(regexp = "^(Savings|Current|Fixed|Recurring)$", message = "Account type must be Savings, Current, Fixed, or Recurring")
        private String accountType;

        @NotBlank(message = "Account holder name is required")
        @Size(min = 2, max = 100, message = "Account holder name must be between 2 and 100 characters")
        private String accountHolderName;

        @PositiveOrZero(message = "Interest rate must be zero or positive")
        private double interestRate;

        @Size(max = 500, message = "Status description must be less than 500 characters")
        private String accountStatusDescription;

        @NotBlank(message = "Account open date is required")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Account open date must be in format YYYY-MM-DD")
        private String accountOpenDate;

        @Pattern(regexp = "^$|^\\d{4}-\\d{2}-\\d{2}$", message = "Account close date must be in format YYYY-MM-DD or empty")
        private String accountCloseDate;

        @OneToOne(mappedBy = "account")
        @NotNull(message = "User must be linked to the account")
        private User user;
}
