package com.banking.backend.Banking_Backend.repository;

import com.banking.backend.Banking_Backend.entites.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardsRepository extends JpaRepository<Cards, Long> {
    List<Cards> findByUser_UserId(Long userId);

}
