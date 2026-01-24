package org.example.demoday2;

class Bank{
    public String bankName;
    private double balance;

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance){
        this.balance=balance;
    }
}

public class Encapsulation {
    public static void main(String[] args) {
        Bank obj = new Bank();
        obj.setBalance(45.23);
        obj.bankName = "State Bank of India";
        System.out.println("BankName : " + obj.bankName);
        System.out.println("Balance : " + obj.getBalance());
    }
}
