package com.example.budgettracker.database.repositories;

import com.example.budgettracker.database.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByUserId(Long id);

}
