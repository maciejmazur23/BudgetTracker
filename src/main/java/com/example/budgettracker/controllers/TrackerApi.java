package com.example.budgettracker.controllers;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.model.Summaries;
import com.example.budgettracker.model.Summary;
import com.example.budgettracker.model.Year;
import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.repositories.TransactionRepo;
import com.example.budgettracker.repositories.UserRepo;
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
import java.util.*;

@Controller
public class TrackerApi {
    private final UserRepo userRepo;
    private final TransactionRepo transactionRepo;
    private final UserService userService;
    private final TransactionService transactionService;
    private final SummaryService summaryService;

    public TrackerApi(UserRepo userRepo, TransactionRepo transactionRepo, UserService userService,
                      TransactionService transactionService, SummaryServiceImpl summaryService) {
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
        this.userService = userService;
        this.transactionService = transactionService;
        this.summaryService = summaryService;
    }

    @GetMapping("/user/transactions")
    public String get(Principal principal, Model model) {
        Long id = userService.getIdByEmail(principal.getName());
        List<Transaction> transactionList = transactionRepo.findByUserId(id);

        Transaction transaction = new Transaction();
        model.addAttribute("transactionList", transactionList);
        model.addAttribute("newTransaction", transaction);

        return "transaction-page";
    }

    @PostMapping("/user/add-transaction")
    public String addTransaction(@ModelAttribute("newOperation") Transaction transaction, Principal principal) {
        Long id = userService.getIdByEmail(principal.getName());
        transaction.setUserId(id);
        transactionRepo.save(transaction);

        return "redirect:/user/transactions";
    }

    @GetMapping("/user/menu")
    public String menu() {
        return "menu-page";
    }

    @GetMapping("/user/summary/{year}")
    public String summary(@PathVariable("year") Integer year, Principal principal, Model model) {
        String email = principal.getName();
        Long userId = userService.getIdByEmail(email);
        List<Transaction> transactions = transactionRepo.findByUserId(userId);
        Summaries summaries = summaryService.getSummaries(transactions);

        List<Summary> summariesList = summaries.monthSummaries().stream()
                .filter(summary -> summary.getYear() == year)
                .toList();
        List<Integer> years = summaries.yearSummaries().stream()
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
        Set<CATEGORY> keySet = categoryMap.keySet();
        Collection<BigDecimal> values = categoryMap.values();

        model.addAttribute("years", years);
        model.addAttribute("list", summariesList);
        model.addAttribute("x", keySet);
        model.addAttribute("y", values);
        model.addAttribute("chosenYear", new Year());

        return "summary-page";
    }

    @GetMapping("/user/summary")
    public String postYear(@ModelAttribute Year year) {
        int intYear = year.getYear();
        if (year.getYear() == 0) intYear = LocalDate.now().getYear();

        return "redirect:/user/summary/" + intYear;
    }

    @GetMapping("/user/summary/chart")
    public String getChart(Model model) {
//        model.addAttribute("x", x);
//        model.addAttribute("y", y);
        return "circle-chart";
    }
}
