package com.banking.backend.Banking_Backend.services.impl;

import com.banking.backend.Banking_Backend.dto.BeneficiaryDTO;
import com.banking.backend.Banking_Backend.entites.Beneficiary;
import com.banking.backend.Banking_Backend.entites.User;
import com.banking.backend.Banking_Backend.repository.BeneficiaryRepository;
import com.banking.backend.Banking_Backend.repository.UserRepository;
import com.banking.backend.Banking_Backend.request.BeneficiaryRequest;
import com.banking.backend.Banking_Backend.services.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final UserRepository userRepository;

    public BeneficiaryDTO addBeneficiary(Long userId, BeneficiaryRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setUser(user);
        beneficiary.setBeneficiaryName(request.getBeneficiaryName());
        beneficiary.setAccountNumber(request.getBeneficiaryAccountNumber());
        beneficiary.setBankName(request.getBankName());

        return toDTO(beneficiaryRepository.save(beneficiary));
    }

    @Override
    public List<BeneficiaryDTO> getAllBeneficiaries(Long userId) {
        return beneficiaryRepository
                .findByUser_UserId(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private BeneficiaryDTO toDTO(Beneficiary b) {
        BeneficiaryDTO dto = new BeneficiaryDTO();
        dto.setId(b.getId());
        dto.setUserId(b.getUser().getUserId());
        dto.setBeneficiaryName(b.getBeneficiaryName());
        dto.setBeneficiaryAccountNumber(b.getAccountNumber());
        dto.setBankName(b.getBankName());
        return dto;
    }
}
