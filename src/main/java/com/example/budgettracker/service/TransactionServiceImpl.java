package com.example.budgettracker.service;

import com.example.budgettracker.entities.TransactionEntity;
import com.example.budgettracker.model.enums.OPERATION;
import com.example.budgettracker.repositories.TransactionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepo transactionRepo;

    @Override
    public void saveTransaction(TransactionEntity transactionEntity) {
        log.info("Transaction: [{}]", transactionEntity);
        if (transactionEntity.getOperation().equals(OPERATION.INCOME) && transactionEntity.getCategory() != null){
            log.error("Operation INCOME not have category cost!");
            throw new RuntimeException("Operation INCOME not have category cost!");
        }else if (transactionEntity.getOperation().equals(OPERATION.COST) && transactionEntity.getCategory() == null){
            log.error("Operation COST must have category cost!");
            throw new RuntimeException("Operation COST must have category cost!");
        }
        transactionRepo.save(transactionEntity);
    }

    @Override
    public List<TransactionEntity> getTransactionsByUserId(Long id) {
        try{
            return transactionRepo.findByUserId(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
//            throw new RuntimeException("Could not find Transaction by user id!");
        }
    }

    @Override
    public void deleteById(Long id) {
        transactionRepo.deleteById(id);
    }

}
