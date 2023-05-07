package com.example.budgettracker.service;

import com.example.budgettracker.entities.TransactionEntity;
import com.example.budgettracker.model.Summary;

import java.util.List;

public interface SummaryCreator {

    void setMonthAndYearSummaries(List<Summary> yearSummaries,
                                  List<Summary> monthSummaries,
                                  List<TransactionEntity> transactionEntities);

}
