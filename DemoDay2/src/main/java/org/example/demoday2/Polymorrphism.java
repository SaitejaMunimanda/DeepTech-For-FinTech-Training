package org.example.demoday2;
class BankAccount1 {
    double balance;

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }
}
class BankAccount2 extends BankAccount1 {

        void deposit(int amount) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }

        void showBalance() {
            System.out.println("Current Balance: " + balance);
        }
    }
public class Polymorrphism {
    public static void main(String[] args) {
        BankAccount2 obj = new BankAccount2();
        obj.deposit(12);
        obj.showBalance();
        obj.deposit(12.56);
        obj.showBalance();

    }


}
