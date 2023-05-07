package com.example.budgettracker.service;

import com.example.budgettracker.entities.TransactionEntity;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.enums.CATEGORY;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SummaryService {
    Summaries getSummaries(List<TransactionEntity> transactionEntities);

    List<BigDecimal> getCosts(List<Summary> summariesList);

    List<BigDecimal> getIncomes(List<Summary> summariesList);

    List<Integer> getYears(List<Summary> yearSummaries);

    List<Summary> getSummariesList(Integer year, Summaries summaries, List<Summary> yearSummaries);

    Map<CATEGORY, BigDecimal> getCategoryCostMap(List<Summary> summariesList);
}
