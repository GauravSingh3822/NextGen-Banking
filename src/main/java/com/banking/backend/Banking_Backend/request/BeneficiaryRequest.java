package com.banking.backend.Banking_Backend.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeneficiaryRequest {

    @NotBlank(message = "Beneficiary name is required")
    @Size(min = 2, max = 100)
    private String beneficiaryName;

    @NotBlank(message = "Account number is required")
    @Size(min = 6, max = 20)
    private String beneficiaryAccountNumber;

    @NotBlank(message = "Bank name is required")
    private String bankName;
}
