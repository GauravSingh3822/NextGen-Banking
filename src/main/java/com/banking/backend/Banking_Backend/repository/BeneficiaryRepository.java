package com.banking.backend.Banking_Backend.repository;

import com.banking.backend.Banking_Backend.entites.Account;
import com.banking.backend.Banking_Backend.entites.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long>{
    List<Beneficiary> findByUser_UserId(Long userId);


}
