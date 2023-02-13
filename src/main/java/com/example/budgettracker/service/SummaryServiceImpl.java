package com.example.budgettracker.service;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.MonthSummary;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.YearSummary;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.model.enums.OPERATION;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SummaryServiceImpl implements SummaryService {

    @Override
    public Summaries getSummaries(List<Transaction> transactions) {
        List<Summary> yearSummaries = new ArrayList<>();
        List<Summary> monthSummaries = new ArrayList<>();

        setMonthAndYearSummaries(yearSummaries, monthSummaries, transactions);

        return new Summaries(monthSummaries, yearSummaries);
    }

    private void setMonthAndYearSummaries(List<Summary> yearSummaries, List<Summary> monthSummaries, List<Transaction> transactions) {
        Map<Integer, List<Transaction>> collectByMonth = transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getDate().getYear()));

        for (Integer year : collectByMonth.keySet()) {
            List<Transaction> yearTransactions = collectByMonth.get(year);
            yearSummaries.add(getYearSummary(year, yearTransactions));

            Arrays.stream(Month.values()).forEach(month -> monthSummaries.add(getMonthSummary(year, month,
                    yearTransactions.stream()
                            .filter(transaction -> transaction.getDate().getMonth().equals(month))
                            .toList())));
        }

        Comparator<Summary> yearComparator = Comparator.comparing(Summary::getYear);
        Comparator<Summary> monthComparator = Comparator.comparing(Summary::getMonth);
        setPreviousBalance(yearSummaries, yearComparator);
        setPreviousBalance(monthSummaries, monthComparator);
    }

    private void setPreviousBalance(List<Summary> summaries, Comparator<Summary> comparator) {
        List<Summary> sortedSummaryList = summaries.stream()
                .sorted(comparator)
                .toList();
        sortedSummaryList.get(0).setPreviousBalance(BigDecimal.ZERO);
        BigDecimal previousBalance = sortedSummaryList.get(0).getCurrentBalance();
        int i = 0;
        for (Summary summary : sortedSummaryList) {
            if (i != 0) summary.setPreviousBalance(previousBalance);
            i++;
            previousBalance = summary.getCurrentBalance();
        }
    }

    private YearSummary getYearSummary(int year, List<Transaction> list) {
        YearSummary yearSummary = new YearSummary();
        setFields(year, list, yearSummary);
        return yearSummary;
    }

    private MonthSummary getMonthSummary(Integer year, Month month, List<Transaction> monthList) {
        MonthSummary monthSummary = new MonthSummary();
        setFields(year, monthList, monthSummary);
        monthSummary.setMonth(month);
        return monthSummary;
    }

    private void setFields(Integer year, List<Transaction> monthList, Summary summary) {
        Map<CATEGORY, BigDecimal> categoryCosts = getCategoryCostMap();

        BigDecimal incomes = BigDecimal.ZERO;
        BigDecimal costs = BigDecimal.ZERO;

        for (Transaction transaction : monthList) {
            CATEGORY category = transaction.getCategory();
            BigDecimal price = transaction.getPrice();

            if (transaction.getOperation().equals(OPERATION.COST)) {
                costs = costs.add(price);
                categoryCosts.replace(category, categoryCosts.get(category).add(price));
            } else incomes = incomes.add(price);
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
        return categoryCosts;
    }
}
