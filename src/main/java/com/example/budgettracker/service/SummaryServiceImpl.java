package com.example.budgettracker.service;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.model.enums.OPERATION;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SummaryServiceImpl implements SummaryService {

    @Override
    public Summaries getSummaries(List<Transaction> transactions) {
        List<Summary> yearSummaries = new ArrayList<>();
        List<Summary> monthSummaries = new ArrayList<>();

        if (transactions.size() > 0) {
            setMonthAndYearSummaries(yearSummaries, monthSummaries, transactions);
            log.info("YearSummaries: [{}]", yearSummaries);
            log.info("MonthSummaries: [{}]", monthSummaries);
        }
        return new Summaries(monthSummaries, yearSummaries);
    }

    private void setMonthAndYearSummaries(List<Summary> yearSummaries, List<Summary> monthSummaries, List<Transaction> transactions) {
        Map<Integer, List<Transaction>> collectByYear = transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getDate().getYear()));
        log.info("CollectByYear: [{}]", collectByYear);

        for (Integer year : collectByYear.keySet()) {
            List<Transaction> yearTransactions = collectByYear.get(year);
            log.info("YearTransactions: [{}]", yearTransactions);
            yearSummaries.add(getSummary(year, null, yearTransactions));

            Arrays.stream(Month.values()).forEach(month -> monthSummaries.add(
                    getSummary(year, month,
                            yearTransactions.stream()
                                    .filter(transaction -> transaction.getDate().getMonth().equals(month))
                                    .toList())
            ));
        }

        Comparator<Summary> yearComparator = Comparator.comparing(Summary::getYear);
        Comparator<Summary> monthComparator = yearComparator.thenComparing(Summary::getMonth);
        setPreviousBalance(yearSummaries, yearComparator);
        setPreviousBalance(monthSummaries, monthComparator);
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
            if (i != 0) summary.setPreviousBalance(previousBalance);
            log.info("Summary: [{}]", summary);
            i++;
            previousBalance = summary.getCurrentBalance()
                    .add(summary.getPreviousBalance());
            log.info("PreviousBalance: [{}]", previousBalance);
        }
    }

    private Summary getSummary(Integer year, Month month, List<Transaction> transactions) {
        Summary summary = new Summary();
        setFields(year, transactions, summary);

        if (month != null) {
            summary.setMonth(month);
            log.info("MonthSummary: [{}]", summary);
        } else log.info("YearSummary: [{}]", summary);

        return summary;
    }

    private void setFields(Integer year, List<Transaction> monthList, Summary summary) {
        Map<CATEGORY, BigDecimal> categoryCosts = getCategoryCostMap();

        BigDecimal incomes = BigDecimal.ZERO;
        BigDecimal costs = BigDecimal.ZERO;

        for (Transaction transaction : monthList) {
            log.info("Transaction: [{}]", transaction);
            CATEGORY category = transaction.getCategory();
            BigDecimal price = transaction.getPrice();

            if (transaction.getOperation().equals(OPERATION.COST)) {
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