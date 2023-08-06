package com.example.budgettracker.service.interfaces;

import com.example.budgettracker.database.entities.TransactionEntity;
import com.example.budgettracker.model.Summary;

import java.util.List;

public interface SummaryCreator {

    void setMonthAndYearSummaries(List<Summary> yearSummaries,
                                  List<Summary> monthSummaries,
                                  List<TransactionEntity> transactionEntities);

}
