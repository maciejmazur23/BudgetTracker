package com.example.budgettracker.controllers;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.Year;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.service.SummaryService;
import com.example.budgettracker.service.SummaryServiceImpl;
import com.example.budgettracker.service.TransactionService;
import com.example.budgettracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TrackerApi {
    private final UserService userService;
    private final TransactionService transactionService;
    private final SummaryService summaryService;

    public TrackerApi(UserService userService, TransactionService transactionService, SummaryServiceImpl summaryService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.summaryService = summaryService;
    }

    @GetMapping("/user/transactions")
    public String get(Principal principal, Model model) {
        Long id = userService.getIdByEmail(principal.getName());
        List<Transaction> transactionList = transactionService.getTransactionsByUserId(id).stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .toList();

        Transaction transaction = new Transaction();
        model.addAttribute("transactionList", transactionList);
        model.addAttribute("newTransaction", transaction);

        return "transaction-page";
    }

    @PostMapping("/user/add-transaction")
    public String addTransaction(@ModelAttribute("newOperation") Transaction transaction, Principal principal) {
        Long id = userService.getIdByEmail(principal.getName());
        transaction.setUserId(id);
        transactionService.saveTransaction(transaction);

        return "redirect:/user/transactions";
    }

    @GetMapping("/user/delete-transaction/{id}")
    public String deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteById(id);
        return "redirect:/user/transactions";
    }

    @GetMapping("/user")
    public String menu() {
        return "menu-page";
    }

    @GetMapping("/user/summary/{year}")
    public String summary(@PathVariable("year") Integer year, Principal principal, Model model) {
        String email = principal.getName();
        Long userId = userService.getIdByEmail(email);
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        Summaries summaries = summaryService.getSummaries(transactions);

        List<Summary> yearSummaries = summaries.yearSummaries();
        List<Summary> summariesList;

        if (year == 1) summariesList = yearSummaries;
        else summariesList = summaries.monthSummaries().stream()
                .filter(summary -> summary.getYear() == year)
                .toList();

        List<Integer> years = yearSummaries.stream()
                .map(Summary::getYear)
                .toList();

        Map<CATEGORY, BigDecimal> categoryMap = new HashMap<>();
        categoryMap.put(CATEGORY.SPORT, BigDecimal.ZERO);
        categoryMap.put(CATEGORY.ENTERTAINMENT, BigDecimal.ZERO);
        categoryMap.put(CATEGORY.FOOD, BigDecimal.ZERO);
        categoryMap.put(CATEGORY.HOUSE, BigDecimal.ZERO);
        categoryMap.put(CATEGORY.OTHER, BigDecimal.ZERO);
        categoryMap.put(CATEGORY.SHOPPING, BigDecimal.ZERO);
        categoryMap.put(CATEGORY.TRANSPORT, BigDecimal.ZERO);

        summariesList.forEach(summary -> {
            Map<CATEGORY, BigDecimal> categoryCosts = summary.getCategoryCosts();
            categoryCosts.forEach(
                    (key, value) -> categoryMap.replace(key, categoryMap.get(key).add(value)));
        });
        List<BigDecimal> incomes = summariesList.stream().map(Summary::getIncomes).toList();
        List<BigDecimal> costs = summariesList.stream().map(Summary::getCosts).toList();

        model.addAttribute("years", years);
        model.addAttribute("list", summariesList);
        model.addAttribute("costs", costs);
        model.addAttribute("incomes", incomes);
        model.addAttribute("months", Month.values());
        model.addAttribute("categoryCosts", categoryMap);
        model.addAttribute("chosenYear", new Year());
        model.addAttribute("deleteInt",new Year());

        return "summary-page";
    }

    @GetMapping("/user/summary")
    public String postYear(@ModelAttribute Year year) {
        int intYear = year.getYear();
        if (year.getYear() == 0) intYear = LocalDate.now().getYear();

        return "redirect:/user/summary/" + intYear;
    }
}
