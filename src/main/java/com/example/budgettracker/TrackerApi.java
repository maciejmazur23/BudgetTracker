package com.example.budgettracker;

import com.example.budgettracker.entities.Transaction;
import com.example.budgettracker.repositories.OperationRepo;
import com.example.budgettracker.repositories.UserRepo;
import com.example.budgettracker.service.OperationService;
import com.example.budgettracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class TrackerApi {
    private final UserRepo userRepo;
    private final OperationRepo operationRepo;
    private final UserService userService;
    private final OperationService operationService;

    public TrackerApi(UserRepo userRepo, OperationRepo operationRepo, UserService userService, OperationService operationService) {
        this.userRepo = userRepo;
        this.operationRepo = operationRepo;
        this.userService = userService;
        this.operationService = operationService;
    }

    @GetMapping("/user/transactions")
    public String get(Principal principal, Model model){
        Long id = userService.getIdByEmail(principal.getName());
        List<Transaction> transactionList = operationRepo.findByUserId(id);
        Transaction transaction = new Transaction();
        model.addAttribute("transactionList", transactionList);
        model.addAttribute("newTransaction", transaction);
        return "transaction-page";
    }

    @PostMapping("/user/add-transaction")
    public String addTransaction(@ModelAttribute("newOperation") Transaction transaction, Principal principal){
        System.out.println("Operation: ");
        System.out.println(transaction);
        String email = principal.getName();
        Long id = userService.getIdByEmail(email);
        transaction.setUserId(id);
        operationRepo.save(transaction);
        return "redirect:/user/transactions";
    }

    @GetMapping("/user/menu")
    public String menu(Principal principal, Model model){
        return "menu-page";
    }

    @GetMapping("/user/summary")
    public String summary(Principal principal, Model model){
        return "summary-page";
    }

}
