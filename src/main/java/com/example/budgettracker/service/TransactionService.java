package com.example.budgettracker.service;

import com.example.budgettracker.entities.TransactionEntity;

import java.util.List;

public interface TransactionService {

    void saveTransaction(TransactionEntity transactionEntity);

    List<TransactionEntity> getTransactionsByUserId(Long id);

    void deleteById(Long id);
}
