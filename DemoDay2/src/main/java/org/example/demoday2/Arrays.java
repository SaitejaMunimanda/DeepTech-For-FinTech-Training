package org.example.demoday2;

public class Arrays {
    public static void main(String[] args) {

        int arr[] = {1, 8, 6, 7, 9, 4};
        System.out.println("First Element : "+arr[0]);
        System.out.println("Third Element : " + arr[2]);

        String[] names = {"Charan","Saiteja","SravnKumar","Shivani","Deepika"};
        System.out.println("First Name : "+names[0]);
        System.out.println("First Name : "+names[0]);
        System.out.println("Third Name : " + names[2]);
        for(int i = 0;i<names.length;i++){
            System.out.print(i + " " + names[i] + " ");
        }


    }
}
