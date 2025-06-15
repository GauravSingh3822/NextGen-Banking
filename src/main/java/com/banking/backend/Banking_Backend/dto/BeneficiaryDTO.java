package com.banking.backend.Banking_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryDTO {

    private Long id;
    private Long userId;                        // ID of the user who added the beneficiary
    private String beneficiaryName;
    private String beneficiaryAccountNumber;
    private String bankName;
}
