package org.example.demoday2;

public class Functions {
    public static double calculateTax(double amount, double rate){
        return amount * rate;
    }
    public static void sendusernameEmail(String userEmail , String userName){
        System.out.println("sending to " + userEmail);
        System.out.println("hello " + userName);
    }

    public static void main(String[] args) {
        double tax = calculateTax(45.23,89.56);
        System.out.println("Tax Amount1 : " + tax);
        double tax1 = calculateTax(89.23,23.56);
        System.out.println("Tax Amount2 : " + tax1);

        sendusernameEmail("saiteja@gmail.com","saiteja");

    }
}
