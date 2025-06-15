package com.banking.backend.Banking_Backend.services.impl;

import com.banking.backend.Banking_Backend.dto.LoanDTO;
import com.banking.backend.Banking_Backend.entites.Loan;
import com.banking.backend.Banking_Backend.entites.User;
import com.banking.backend.Banking_Backend.repository.LoanRepository;
import com.banking.backend.Banking_Backend.repository.UserRepository;
import com.banking.backend.Banking_Backend.request.LoanRequest;
import com.banking.backend.Banking_Backend.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    // 1. Apply for Loan
    @Override
    public LoanDTO applyLoan(LoanRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Loan loan = new Loan();
        loan.setLoanType(request.getLoanType());
        loan.setAmount(request.getAmount());
        loan.setInterestRate(request.getInterestRate());
        loan.setUser(user);
        loan.setStatus("PENDING");

        return toDTO(loanRepository.save(loan));
    }

    // 2. Get Loans by User
    @Override
    public List<LoanDTO> getLoansByUserId(Long userId) {
        return loanRepository.findByUser_UserId(userId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // 3. Approve Loan
    @Override
    public LoanDTO approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus("APPROVED");
        return toDTO(loanRepository.save(loan));
    }

    // 4. Reject Loan
    @Override
    public LoanDTO rejectLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus("REJECTED");
        return toDTO(loanRepository.save(loan));
    }

    // 5. Update Loan Status
    @Override
    public LoanDTO updateLoanStatus(Long loanId, String status) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(status.toUpperCase());
        return toDTO(loanRepository.save(loan));
    }

    // 6. Get Loan By ID
    @Override
    public LoanDTO getLoanById(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        return toDTO(loan);
    }

    // 7. Delete Loan
    @Override
    public void deleteLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loanRepository.delete(loan);
    }

    // Mapper
    private LoanDTO toDTO(Loan loan) {
        LoanDTO dto = new LoanDTO();
        dto.setId(loan.getId());
        dto.setLoanType(loan.getLoanType());
        dto.setAmount(loan.getAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setUserId(loan.getUser().getUserId());
        dto.setStatus(loan.getStatus());
        return dto;
    }
}
