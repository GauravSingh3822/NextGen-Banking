package com.banking.backend.Banking_Backend.services;
import com.banking.backend.Banking_Backend.dto.AccountDTO;
import com.banking.backend.Banking_Backend.entites.Account;
import com.banking.backend.Banking_Backend.request.AccountRequest;

import java.util.List;
import java.util.Optional;

public interface AccountServices {

        AccountDTO createAccount(Long userId, AccountRequest request);

        Optional<Account> getAccount(Long accountId);
        List<AccountDTO> getAccountsByUserId(Long userId);


}
