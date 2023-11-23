package com.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean number = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Library Portal Initialized.");
        while (number == true) {
            System.out.println("---------------------------");
            System.out.println("1.Enter as a librarian");
            System.out.println("2.Enter as a member");
            System.out.println("3.Exit");
            System.out.println("----------------------------");
            try {
                int option = sc.nextInt();
                if (option == 1) {
                    Librarian libre = new Librarian();
                    libre.menu();
                } else if (option == 2) {
                    Member mem = new Member();
                    mem.enter();
                } else if (option == 3) {
                    System.out.println("---------------------");
                    System.out.println("Thanks for visiting.");
                    System.out.println("-------------------------");
                    number = false;
                } else {
                    System.out.println("Choose a valid option app case.");
                }

            } catch (Exception e) {
                System.out.println("Please enter valid commands.");
            }

        }
    }
}

