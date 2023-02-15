package com.example.budgettracker.service;

import com.example.budgettracker.entities.Transaction;

import java.util.List;

public interface TransactionService {

    void saveTransaction(Transaction transaction);

    List<Transaction> getTransactionsByUserId(Long id);

    void deleteById(Long id);
}
