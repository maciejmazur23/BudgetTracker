package com.example.budgettracker.service.interfaces;

import com.example.budgettracker.database.entities.TransactionEntity;
import com.example.budgettracker.model.TransactionFilter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    void saveTransaction(TransactionEntity transactionEntity);

    List<TransactionEntity> getTransactionsByUserId(Long id);

    void deleteById(Long id);

    List<TransactionEntity> getTransactionsByFilter(TransactionFilter filter);

    TransactionFilter getFilter(Optional<TransactionFilter> optionalFilter, Long id);

    List<TransactionEntity> sortTransactions(Long id, List<TransactionEntity> transactions, Comparator<TransactionEntity> reversed);
}
