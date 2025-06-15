package com.banking.backend.Banking_Backend.services.impl;

import com.banking.backend.Banking_Backend.dto.TransactionDTO;
import com.banking.backend.Banking_Backend.entites.Account;
import com.banking.backend.Banking_Backend.entites.Transaction;
import com.banking.backend.Banking_Backend.repository.AccountRepository;
import com.banking.backend.Banking_Backend.repository.TransactionRepository;
import com.banking.backend.Banking_Backend.request.TransactionRequest;
import com.banking.backend.Banking_Backend.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public TransactionDTO transfer(TransactionRequest request) {
        Account from = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account to = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (from.getAccountBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        // Deduct and credit balances
        from.setAccountBalance(from.getAccountBalance() - request.getAmount());
        to.setAccountBalance(to.getAccountBalance() + request.getAmount());

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction txn = new Transaction();
        txn.setType("TRANSFER");
        txn.setAmount(request.getAmount());
        txn.setStatus("SUCCESS");
        txn.setCreatedAt(LocalDateTime.now());
        txn.setFromAccountId(from.getId());
        txn.setToAccountId(to.getId());

        transactionRepository.save(txn);

        return toDTO(txn);
    }

    private TransactionDTO toDTO(Transaction txn) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(txn.getId());
        dto.setType(txn.getType());
        dto.setAmount(txn.getAmount());
        dto.setStatus(txn.getStatus());
        dto.setCreatedAt(String.valueOf(txn.getCreatedAt()));
        dto.setFromAccountId(txn.getFromAccountId());
        dto.setToAccountId(txn.getToAccountId());
        return dto;
    }

    @Override
    public List<TransactionDTO> getTranscationById(Long accountId) {
        return transactionRepository.findByAccountId(accountId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        Transaction txn = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return toDTO(txn);
    }

    // TODO: Implement if needed
    @Override
    public TransactionDTO applyLoan(TransactionRequest request) {
        Account account = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (request.getAmount() <= 0) {
            throw new RuntimeException("Invalid loan amount");
        }

        account.setAccountBalance(account.getAccountBalance() + request.getAmount());
        accountRepository.save(account);

        Transaction txn = new Transaction();
        txn.setType("LOAN");
        txn.setAmount(request.getAmount());
        txn.setStatus("APPROVED");
        txn.setCreatedAt(LocalDateTime.now());
        txn.setFromAccountId(account.getId());

        transactionRepository.save(txn);
        return toDTO(txn);
    }


    @Override
    public void transferFunds(TransactionRequest request) {
        Account from = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));
        Account to = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (request.getAmount() <= 0) {
            throw new RuntimeException("Invalid transfer amount");
        }

        if (from.getAccountBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        // Update balances
        from.setAccountBalance(from.getAccountBalance() - request.getAmount());
        to.setAccountBalance(to.getAccountBalance() + request.getAmount());

        accountRepository.save(from);
        accountRepository.save(to);

        // Record the transaction
        Transaction txn = new Transaction();
        txn.setType("TRANSFER");
        txn.setAmount(request.getAmount());
        txn.setStatus("SUCCESS");
        txn.setCreatedAt(LocalDateTime.now());
        txn.setFromAccountId(from.getId());
        txn.setToAccountId(to.getId());

        transactionRepository.save(txn);
    }


    @Override
    public void deposit(TransactionRequest request) {
        Account account = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (request.getAmount() <= 0) {
            throw new RuntimeException("Invalid deposit amount");
        }

        account.setAccountBalance(account.getAccountBalance() + request.getAmount());
        accountRepository.save(account);

        Transaction txn = new Transaction();
        txn.setType("DEPOSIT");
        txn.setAmount(request.getAmount());
        txn.setStatus("SUCCESS");
        txn.setCreatedAt(LocalDateTime.now());
        txn.setFromAccountId(account.getId());

        transactionRepository.save(txn);
    }

    @Override
    public void withdraw(TransactionRequest request) {
        Account account = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (request.getAmount() <= 0) {
            throw new RuntimeException("Invalid withdraw amount");
        }

        if (account.getAccountBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setAccountBalance(account.getAccountBalance() - request.getAmount());
        accountRepository.save(account);

        Transaction txn = new Transaction();
        txn.setType("WITHDRAW");
        txn.setAmount(request.getAmount());
        txn.setStatus("SUCCESS");
        txn.setCreatedAt(LocalDateTime.now());
        txn.setFromAccountId(account.getId());

        transactionRepository.save(txn);
    }


    @Override
    public List<TransactionDTO> getTransactionsForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Account account = accountRepository.findByUser_Name(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        List<Transaction> transactions = transactionRepository
                .findByFromAccountIdOrToAccountId(account.getId(), account.getId());

        return transactions.stream().map(this::toDTO).collect(Collectors.toList());
    }

}


