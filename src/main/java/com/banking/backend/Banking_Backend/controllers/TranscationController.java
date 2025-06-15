package com.banking.backend.Banking_Backend.controllers;

import com.banking.backend.Banking_Backend.dto.TransactionDTO;
import com.banking.backend.Banking_Backend.entites.Transaction;
import com.banking.backend.Banking_Backend.request.TransactionRequest;
import com.banking.backend.Banking_Backend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user/transcation")
public class TranscationController {

    @Autowired
    private TransactionService transactionService;

//=============================================================================================

    //1.getAllTranscation() method to get all transactions of a user from the database.
    //2. deposite() method to create a new deposit transaction.
    //3. withdraw() method to create a new withdraw transaction.
    //4. transfer() method to create a new transfer transaction.
    //5. getDeposit() method to get all deposit transactions of a user from the database.
    //6. getWithdraw() method to get all withdraw transactions of a user from the database.
    //7. getTransfer() method to get all transfer transactions of a user from the database.
    //8. getTransactionById() method to get a transaction by its ID from the database.

//==============================================================================================

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getTransactionsForCurrentUser());
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransactionRequest request) {
        transactionService.transferFunds(request);
        return ResponseEntity.ok("Funds transferred successfully");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositFunds(@RequestBody TransactionRequest request) {
        transactionService.deposit(request);
        return ResponseEntity.ok("Amount deposited successfully");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawFunds(@RequestBody TransactionRequest request) {
        transactionService.withdraw(request);
        return ResponseEntity.ok("Amount withdrawn successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }
}
