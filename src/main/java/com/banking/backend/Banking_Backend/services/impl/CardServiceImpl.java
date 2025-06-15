package com.banking.backend.Banking_Backend.services.impl;

import com.banking.backend.Banking_Backend.dto.CardDTO;
import com.banking.backend.Banking_Backend.entites.Cards;
import com.banking.backend.Banking_Backend.entites.User;
import com.banking.backend.Banking_Backend.repository.CardsRepository;
import com.banking.backend.Banking_Backend.repository.UserRepository;
import com.banking.backend.Banking_Backend.request.CardRequest;
import com.banking.backend.Banking_Backend.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardsRepository cardRepository;
    private final UserRepository userRepository;

    @Override
    public CardDTO issueCard(CardRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cards card = new Cards();
        card.setCardNumber(request.getCardNumber());
        card.setCardType(request.getCardType());
        card.setIssueDate(request.getIssueDate());
        card.setExpiryDate(request.getExpiryDate());
        card.setUser(user);

        return toDTO(cardRepository.save(card));
    }

    @Override
    public List<CardDTO> getCardsByUserId(Long userId) {
        return cardRepository.findByUser_UserId(userId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }


    @Override
    public CardDTO applyCard(CardRequest request) {
        return null;
    }

    private CardDTO toDTO(Cards card) {
        CardDTO dto = new CardDTO();
        dto.setId(card.getId());
        dto.setCardNumber(card.getCardNumber());
        dto.setCardType(card.getCardType());
        dto.setIssueDate(card.getIssueDate());
        dto.setExpiryDate(card.getExpiryDate());
        dto.setUserId(card.getUser().getUserId());
        return dto;
    }
    @Override
    public CardDTO updateCard(Long cardId, CardRequest request) {
        Cards card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        card.setCardType(request.getCardType());
        card.setExpiryDate(request.getExpiryDate());

        return toDTO(cardRepository.save(card));
    }

    @Override
    public CardDTO getCardById(Long cardId) {
        Cards card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        return toDTO(card);
    }

    @Override
    public void deleteCard(Long cardId) {
        Cards card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        cardRepository.delete(card);
    }

    @Override
    public CardDTO blockCard(Long cardId) {
        Cards card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        card.setBlocked(true); // make sure there's a `blocked` field in entity
        return toDTO(cardRepository.save(card));
    }

}
