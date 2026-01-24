package org.example.demoday2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(1, 15);
        //System.out.println("Numbers: " + numbers);

        int var = numbers.get(1);
        System.out.println("second element: " + var);

        numbers.remove(2);
        System.out.println("After remove: " + numbers);



        ArrayList<String> names = new ArrayList<>();
        names.add("Saiteja");
        names.add("Rahul");
        names.add("Anjali");
        System.out.println("Names: " + names);

        names.set(1, "Ravi");
        System.out.println("Updated Names: " + names);

        System.out.println("Get index 0: " + names.get(0));

        List<Double> prices = new ArrayList<>();
        prices.add(99.99);
        prices.add(199.50);
        prices.add(49.75);

        System.out.println("Prices: " + prices);

        HashMap<Integer, String> students = new HashMap<>();

        students.put(101, "Saiteja");
        students.put(102, "Rahul");
        students.put(103, "Anjali");
        System.out.println(students.get(101));

        System.out.println("Students Map: " + students);

        HashSet<Integer> numbers1 = new HashSet<>();
        numbers1.add(10);
        numbers1.add(20);
        numbers1.add(30);
        numbers1.add(10); //dup

        System.out.println("Numbers: " + numbers);

    }
}
