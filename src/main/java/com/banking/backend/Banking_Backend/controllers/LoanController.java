package com.banking.backend.Banking_Backend.controllers;

import com.banking.backend.Banking_Backend.dto.LoanDTO;
import com.banking.backend.Banking_Backend.request.LoanRequest;
import com.banking.backend.Banking_Backend.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    // 1. Apply Loan
    @PostMapping("/apply")
    public ResponseEntity<LoanDTO> applyLoan(@RequestBody LoanRequest request) {
        LoanDTO loan = loanService.applyLoan(request);
        return ResponseEntity.ok(loan);
    }

    // 2. Approve Loan
    @PutMapping("/approve/{id}")
    public ResponseEntity<LoanDTO> approveLoan(@PathVariable Long id) {
        LoanDTO loan = loanService.approveLoan(id);
        return ResponseEntity.ok(loan);
    }

    // 3. Reject Loan
    @PutMapping("/reject/{id}")
    public ResponseEntity<LoanDTO> rejectLoan(@PathVariable Long id) {
        LoanDTO loan = loanService.rejectLoan(id);
        return ResponseEntity.ok(loan);
    }

    // 4. Update Loan Status by ID
    @PutMapping("/update-status/{id}")
    public ResponseEntity<LoanDTO> updateLoanStatus(@PathVariable Long id, @RequestParam String status) {
        LoanDTO loan = loanService.updateLoanStatus(id, status);
        return ResponseEntity.ok(loan);
    }

    // 5. Get Loan Details by ID
    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
        LoanDTO loan = loanService.getLoanById(id);
        return ResponseEntity.ok(loan);
    }

    // 6. Delete Loan by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.ok("Loan deleted successfully");
    }
}
