package com.example.budgettracker.service;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.Summaries;

import java.util.List;

public interface SummaryService {
    Summaries getSummaries(List<Transaction> transactions);
}
