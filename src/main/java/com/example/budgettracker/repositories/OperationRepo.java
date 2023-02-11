package com.example.budgettracker.repositories;

import com.example.budgettracker.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long id);
}
