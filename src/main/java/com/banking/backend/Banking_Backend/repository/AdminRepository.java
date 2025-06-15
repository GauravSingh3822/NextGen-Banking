package com.banking.backend.Banking_Backend.repository;

import com.banking.backend.Banking_Backend.entites.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
