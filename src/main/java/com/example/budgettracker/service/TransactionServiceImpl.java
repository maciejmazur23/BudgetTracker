package com.example.budgettracker.service;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.enums.OPERATION;
import com.example.budgettracker.repositories.TransactionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepo transactionRepo;

    @Override
    public void saveTransaction(Transaction transaction) {
        log.warn("Transaction: [{}]", transaction);
        if (transaction.getOperation().equals(OPERATION.INCOME) && transaction.getCategory() != null){
            log.error("Operation INCOME not have category cost!");
            throw new RuntimeException("Operation INCOME not have category cost!");
        }else if (transaction.getOperation().equals(OPERATION.COST) && transaction.getCategory() == null){
            log.error("Operation COST must have category cost!");
            throw new RuntimeException("Operation COST must have category cost!");
        }
        transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(Long id) {
        return transactionRepo.findByUserId(id);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepo.deleteById(id);
    }

}
