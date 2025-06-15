package com.banking.backend.Banking_Backend.controllers;

import com.banking.backend.Banking_Backend.dto.CardDTO;
import com.banking.backend.Banking_Backend.request.CardRequest;
import com.banking.backend.Banking_Backend.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    // 1. Apply for a new credit card
    @PostMapping("/apply")
    public ResponseEntity<CardDTO> applyCard(@RequestBody CardRequest request) {
        return ResponseEntity.ok(cardService.applyCard(request));
    }

    // 2. Update credit card details by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable Long id, @RequestBody CardRequest request) {
        return ResponseEntity.ok(cardService.updateCard(id, request));
    }

    // 3. Get credit card details by ID
    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    // 4. Delete credit card by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok("Card deleted successfully");
    }

    //  5. Block credit card by ID
    @PutMapping("/block/{id}")
    public ResponseEntity<CardDTO> blockCard(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.blockCard(id));
    }
}
