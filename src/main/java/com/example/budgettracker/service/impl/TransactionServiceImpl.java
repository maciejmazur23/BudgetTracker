package com.example.budgettracker.service.impl;

import com.example.budgettracker.database.entities.TransactionEntity;
import com.example.budgettracker.database.repositories.TransactionRepo;
import com.example.budgettracker.model.TransactionFilter;
import com.example.budgettracker.model.enums.OPERATION;
import com.example.budgettracker.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;

    @Override
    public void saveTransaction(TransactionEntity transactionEntity) {
        log.info("Transaction: [{}]", transactionEntity);
        if (transactionEntity.getOperation().equals(OPERATION.INCOME) && transactionEntity.getCategory() != null) {
            log.error("Operation INCOME not have category cost!");
            throw new RuntimeException("Operation INCOME not have category cost!");
        } else if (transactionEntity.getOperation().equals(OPERATION.COST) && transactionEntity.getCategory() == null) {
            log.error("Operation COST must have category cost!");
            throw new RuntimeException("Operation COST must have category cost!");
        }
        transactionRepo.save(transactionEntity);
    }

    @Override
    public List<TransactionEntity> getTransactionsByUserId(Long id) {
        try {
            return transactionRepo.findByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException("Could not find transaction by user id!");
        }
    }

    @Override
    public void deleteById(Long id) {
        transactionRepo.deleteById(id);
    }

    @Override
    public List<TransactionEntity> getTransactionsByFilter(TransactionFilter filter) {
        List<TransactionEntity> transactions = transactionRepo.findByUserId(filter.getId());

        try {
            return transactions.stream()
                    .filter(transactionEntity -> transactionEntity.getDate().compareTo(filter.getDateFrom()) > 0)
                    .filter(transactionEntity -> transactionEntity.getDate().compareTo(filter.getDateTo()) < 0)
                    .filter(transactionEntity -> categoryFilter(filter, transactionEntity))
                    .filter(transactionEntity -> transactionEntity.getPrice().compareTo(filter.getPriceFrom()) > 0)
                    .filter(transactionEntity -> transactionEntity.getPrice().compareTo(filter.getPriceTo()) < 0)
                    .filter(transactionEntity -> operationFilter(filter, transactionEntity))
                    .toList();
        } catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TransactionFilter getFilter(Optional<TransactionFilter> optionalFilter, Long id) {
        TransactionFilter filter = new TransactionFilter();

        if (optionalFilter.isPresent()) {
            TransactionFilter optionalFilterValue = optionalFilter.get();
            if (optionalFilterValue.getDateFrom() == null) optionalFilterValue.setDateFrom(filter.getDateFrom());
            if (optionalFilterValue.getDateTo() == null) optionalFilterValue.setDateTo(filter.getDateTo());
            filter = optionalFilterValue;
        }
        filter.setId(id);

        return filter;
    }

    @Override
    public List<TransactionEntity> sortTransactions(
            Long id, List<TransactionEntity> transactions, Comparator<TransactionEntity> comparator
    ) {
        return transactions.stream()
                .sorted(comparator)
                .toList();
    }

    @Override
    public void deleteAll() {
        transactionRepo.deleteAll();
    }

    private boolean operationFilter(TransactionFilter filter, TransactionEntity transactionEntity) {
        if (filter.isIncomes() && filter.isCosts()) return true;
        else if (!filter.isIncomes() && !filter.isCosts()) return false;
        else if (filter.isIncomes()) return transactionEntity.getOperation() == OPERATION.INCOME;
        else return transactionEntity.getOperation() == OPERATION.COST;
    }

    private boolean categoryFilter(TransactionFilter filter, TransactionEntity transactionEntity) {
        if (filter.getCategories().size() == 0) return true;
        else return filter.getCategories().contains(transactionEntity.getCategory());
    }

}