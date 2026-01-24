package org.example.springbootproject;

import org.springframework.stereotype.Service;
// tells Spring this is a service class
@Service
public class BankService {

    public String getAccountType() {
        return "Savings Account with 3% Interest";
    }
}

