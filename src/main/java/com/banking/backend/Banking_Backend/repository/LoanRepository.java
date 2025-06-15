package com.banking.backend.Banking_Backend.repository;

import com.banking.backend.Banking_Backend.entites.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUser_UserId(Long userId);
}
