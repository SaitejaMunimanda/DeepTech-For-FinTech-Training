package org.example.demoday2;

class Account {
    String accountNumber;
    double balance;

    void showBalance() {
        System.out.println("Balance: " + balance);
    }
}

class SavingsAccount extends Account {
    double interestRate;

    void showInterest() {
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount();
        sa.accountNumber = "SAV123";
        sa.balance = 5000;
        sa.interestRate = 4.5;

        sa.showBalance();
        sa.showInterest();
    }
}

