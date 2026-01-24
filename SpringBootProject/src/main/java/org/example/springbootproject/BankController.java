package org.example.springbootproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class BankController {
    // auto connects BankService
    @Autowired
    private BankService bankService;

    // URL mapping
    @GetMapping("/bank")
    public String showAccount() {
        String accountType = bankService.getAccountType();

        System.out.println("Account Type: " + accountType);
        return bankService.getAccountType();
    }
}

