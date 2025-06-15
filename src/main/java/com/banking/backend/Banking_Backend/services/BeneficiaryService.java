package com.banking.backend.Banking_Backend.services;

import com.banking.backend.Banking_Backend.dto.BeneficiaryDTO;
import com.banking.backend.Banking_Backend.request.BeneficiaryRequest;

import java.util.List;

public interface BeneficiaryService {
    BeneficiaryDTO addBeneficiary(Long userId, BeneficiaryRequest request);
    List<BeneficiaryDTO> getAllBeneficiaries(Long userId);
}

