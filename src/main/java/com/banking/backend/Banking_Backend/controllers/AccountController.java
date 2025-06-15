package com.banking.backend.Banking_Backend.controllers;

import com.banking.backend.Banking_Backend.dto.AccountDTO;
import com.banking.backend.Banking_Backend.entites.Account;
import com.banking.backend.Banking_Backend.request.AccountRequest;
import com.banking.backend.Banking_Backend.services.AccountServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

//==========================================================================
    //1. Create a new account
    //2. Deposit funds into an existing account
    //3. Withdraw funds from an existing account
    //4. Get Account
    //5. Update Account
    //6. Delete Account
//==========================================================================

    @Autowired
    private AccountServices accountServices;

    //1. Create a new account
    @PostMapping("/{userId}")
    public ResponseEntity<AccountDTO> createAccoun(@PathVariable Long userId, @RequestBody @Valid AccountRequest accountRequest){
        return ResponseEntity.ok(accountServices.createAccount(userId, accountRequest));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Optional<Account>> getAccount(@PathVariable Long accountId){
        return ResponseEntity.ok(accountServices.getAccount(accountId));
    }

    //2. Deposit funds into an existing account
    //3. Withdraw funds from an existing account
    //4. Get Account
    //5. Update Account
    //6. Delete Account
}
