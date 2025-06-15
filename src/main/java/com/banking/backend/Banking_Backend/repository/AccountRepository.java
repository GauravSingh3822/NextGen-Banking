package com.banking.backend.Banking_Backend.repository;

import com.banking.backend.Banking_Backend.entites.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long>{


    List<Account> findByUser_UserId(Long userId);
    Optional<Account> findByUser_Name(String name);
    // <-- Correct method
    }

