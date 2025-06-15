package com.banking.backend.Banking_Backend.services;

import com.banking.backend.Banking_Backend.dto.TransactionDTO;
import com.banking.backend.Banking_Backend.request.TransactionRequest;

import java.util.List;

public interface TransactionService {

    TransactionDTO applyLoan(TransactionRequest request);

    TransactionDTO transfer(TransactionRequest request);

    List<TransactionDTO> getTranscationById(Long userId);

    List<TransactionDTO> getTransactionsForCurrentUser();

    void transferFunds(TransactionRequest request);

    void deposit(TransactionRequest request);

    void withdraw(TransactionRequest request);

    TransactionDTO getTransactionById(Long id);
}
