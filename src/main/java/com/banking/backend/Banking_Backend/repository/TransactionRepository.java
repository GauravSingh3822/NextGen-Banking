package com.banking.backend.Banking_Backend.repository;

import com.banking.backend.Banking_Backend.entites.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByAccountId(Long accountId);
    List<Transaction> findByFromAccountIdOrToAccountId(Long fromId, Long toId);

}
