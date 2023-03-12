package com.example.budgettracker.controllers;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.service.TransactionService;
import com.example.budgettracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TransactionsApi {
    private final UserService userService;
    private final TransactionService transactionService;

    @GetMapping("/user/transactions")
    public String get(Principal principal, Model model) {
        Long id = userService.getIdByEmail(principal.getName());
        log.debug("Id: [{}]", id);
        List<Transaction> transactionList = getSortedTransactions(id, Comparator.comparing(Transaction::getDate).reversed());
        log.info("TransactionList: [{}]", transactionList);

        model.addAttribute("transactionList", transactionList);
        model.addAttribute("newTransaction", new Transaction());

        return "transaction-page";
    }

    private List<Transaction> getSortedTransactions(Long id, Comparator<Transaction> comparator) {
        return transactionService.getTransactionsByUserId(id).stream()
                .sorted(comparator)
                .toList();
    }

    @PostMapping("/user/add-transaction")
    public String addTransaction(@ModelAttribute("newOperation") Transaction transaction, Principal principal) {
        log.info("Transaction: [{}]", transaction);
        Long id = userService.getIdByEmail(principal.getName());
        log.debug("Id: [{}]", id);
        transaction.setUserId(id);
        transactionService.saveTransaction(transaction);

        return "redirect:/user/transactions";
    }

    @GetMapping("/user/delete-transaction/{id}")
    public String deleteTransaction(@PathVariable("id") Long id) {
        log.info("Id: [{}]", id);
        transactionService.deleteById(id);
        return "redirect:/user/transactions";
    }
}
