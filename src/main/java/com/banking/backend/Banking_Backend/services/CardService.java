package com.banking.backend.Banking_Backend.services;

import com.banking.backend.Banking_Backend.dto.CardDTO;
import com.banking.backend.Banking_Backend.request.CardRequest;

import java.util.List;

public interface CardService {
    CardDTO issueCard(CardRequest request);
    List<CardDTO> getCardsByUserId(Long userId);

    CardDTO applyCard(CardRequest request);

    CardDTO updateCard(Long cardId, CardRequest request);

    CardDTO getCardById(Long cardId);

    void deleteCard(Long cardId);

    CardDTO blockCard(Long cardId);
}

