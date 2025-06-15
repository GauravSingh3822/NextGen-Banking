package com.banking.backend.Banking_Backend.services;

import com.banking.backend.Banking_Backend.dto.LoanDTO;
import com.banking.backend.Banking_Backend.request.LoanRequest;

import java.util.List;

public interface LoanService {
    LoanDTO applyLoan(LoanRequest request);
    List<LoanDTO> getLoansByUserId(Long userId);

    // ✅ 3. Approve Loan
    LoanDTO approveLoan(Long loanId);

    // ✅ 4. Reject Loan
    LoanDTO rejectLoan(Long loanId);

    // ✅ 5. Update Loan Status
    LoanDTO updateLoanStatus(Long loanId, String status);

    // ✅ 6. Get Loan By ID
    LoanDTO getLoanById(Long loanId);

    // ✅ 7. Delete Loan
    void deleteLoan(Long loanId);
}
