package com.example.budgettracker.service.impl;

import com.example.budgettracker.database.entities.TransactionEntity;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.model.enums.OPERATION;
import com.example.budgettracker.service.interfaces.SummaryCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SummaryCreatorImpl implements SummaryCreator {

    @Override
    public void setMonthAndYearSummaries(List<Summary> yearSummaries, List<Summary> monthSummaries, List<TransactionEntity> transactionEntities) {
        Map<Integer, List<TransactionEntity>> collectByYear = transactionEntities.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getDate().getYear()));
        log.info("CollectByYear: [{}]", collectByYear);

        for (Integer year : collectByYear.keySet()) {
            List<TransactionEntity> yearTransactionEntities = collectByYear.get(year);
            log.info("YearTransactions: [{}]", yearTransactionEntities);
            yearSummaries.add(getSummary(year, null, yearTransactionEntities));

            Arrays.stream(Month.values()).forEach(month -> monthSummaries.add(
                    getSummary(year, month, yearTransactionEntities.stream()
                            .filter(transaction -> transaction.getDate().getMonth().equals(month))
                            .toList())));
        }

        Comparator<Summary> yearComparator = Comparator.comparing(Summary::getYear);
        Comparator<Summary> monthComparator = yearComparator.thenComparing(Summary::getMonth);
        setPreviousBalance(yearSummaries, yearComparator);
        setPreviousBalance(monthSummaries, monthComparator);
    }

    private Summary getSummary(Integer year, Month month, List<TransactionEntity> transactionEntities) {
        Summary summary = new Summary();
        setFields(year, transactionEntities, summary);

        if (month != null) {
            summary.setMonth(month);
            log.info("MonthSummary: [{}]", summary);
        } else log.info("YearSummary: [{}]", summary);

        return summary;
    }

    private void setPreviousBalance(List<Summary> summaries, Comparator<Summary> comparator) {
        List<Summary> sortedSummaryList = summaries.stream()
                .sorted(comparator)
                .toList();
        log.info("SortedSummaryList: [{}]", sortedSummaryList);

        sortedSummaryList.get(0).setPreviousBalance(BigDecimal.ZERO);
        BigDecimal previousBalance = BigDecimal.ZERO;
        log.info("SortedSummaryList: [{}]", sortedSummaryList);

        int i = 0;
        for (Summary summary : sortedSummaryList) {
            if (i != 0)
                summary.setPreviousBalance(previousBalance);

            if (summary.getMonth() != null && LocalDate.now().compareTo(LocalDate.of(
                    summary.getYear(), summary.getMonth(), 1
            )) < 0) {
                summary.setPreviousBalance(BigDecimal.ZERO);
            }

            log.info("Summary: [{}]", summary);
            i++;
            previousBalance = summary.getCurrentBalance()
                    .add(summary.getPreviousBalance());
            log.info("PreviousBalance: [{}]", previousBalance);
        }
    }

    private void setFields(Integer year, List<TransactionEntity> monthList, Summary summary) {
        Map<CATEGORY, BigDecimal> categoryCosts = getCategoryCostMap();

        BigDecimal incomes = BigDecimal.ZERO;
        BigDecimal costs = BigDecimal.ZERO;

        for (TransactionEntity transactionEntity : monthList) {
            log.info("Transaction: [{}]", transactionEntity);
            CATEGORY category = transactionEntity.getCategory();
            BigDecimal price = transactionEntity.getPrice();

            if (transactionEntity.getOperation().equals(OPERATION.COST)) {
                costs = costs.add(price);
                log.info("Cost: [{}]", costs);
                categoryCosts.replace(category, categoryCosts.get(category).add(price));
                log.info("categoryCosts: [{}]", categoryCosts);
            } else {
                incomes = incomes.add(price);
                log.info("Incomes: [{}]", incomes);
            }

        }

        summary.setYear(year);
        summary.setCosts(costs);
        summary.setIncomes(incomes);
        summary.setCurrentBalance(incomes.subtract(costs));
        summary.setCategoryCosts(categoryCosts);
    }

    private Map<CATEGORY, BigDecimal> getCategoryCostMap() {
        Map<CATEGORY, BigDecimal> categoryCosts = new HashMap<>();
        for (CATEGORY category : CATEGORY.values()) categoryCosts.put(category, BigDecimal.ZERO);
        log.info("CategoryCost: [{}]", categoryCosts);
        return categoryCosts;
    }
}
