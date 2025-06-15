package com.banking.backend.Banking_Backend.services.impl;

import com.banking.backend.Banking_Backend.dto.AccountDTO;
import com.banking.backend.Banking_Backend.entites.Account;
import com.banking.backend.Banking_Backend.entites.User;
import com.banking.backend.Banking_Backend.repository.AccountRepository;
import com.banking.backend.Banking_Backend.repository.UserRepository;
import com.banking.backend.Banking_Backend.request.AccountRequest;

import com.banking.backend.Banking_Backend.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountServices {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;



    @Override
    public AccountDTO createAccount(Long userId, AccountRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Account account = new Account();
        account.setId(generateUniqueAccountId());
        account.setAccountType(request.getAccountType());
        account.setAccountBalance(request.getInitialDeposit());
        account.setAccountHolderName(user.getName());
        account.setAccountOpenDate(java.time.LocalDate.now().toString());
        account.setInterestRate(3.5); // Sample interest rate
        account.setAccountStatusDescription("Active");
        account.setUser(user);

        Account savedAccount = accountRepository.save(account);

        return new AccountDTO(
                savedAccount.getId(),
                savedAccount.getId() + "",
                savedAccount.getAccountType(),
                savedAccount.getAccountBalance(),
                savedAccount.getUser().getUserId()
        );
    }

    @Override
    public Optional<Account> getAccount(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public List<AccountDTO> getAccountsByUserId(Long userId) {
        List<Account> accounts = accountRepository.findByUser_UserId(userId);

        return accounts.stream().map(account ->
                new AccountDTO(
                        account.getId(),
                        account.getId() + "",
                        account.getAccountType(),
                        account.getAccountBalance(),
                        account.getUser().getUserId()
                )
        ).collect(Collectors.toList());
    }

    private long generateUniqueAccountId() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits());
    }
}
