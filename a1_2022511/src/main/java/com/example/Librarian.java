package com.example;

import java.util.Scanner;

public class Librarian {
    private int choice;
    private boolean bool_lib = true;
    private Member new_member;
    Scanner sc2 = new Scanner(System.in);
    public Librarian() {
        System.out.println("------------------------");
    }
    public void menu() {
        while (bool_lib) {
            System.out.println("-----------------------");
            System.out.println("1.Register a member");
            System.out.println("2.Remove a member");
            System.out.println("3.Add a book");
            System.out.println("4.Remove a book");
            System.out.println("5.View all members along with their books and fines to be paid");
            System.out.println("6.View all books");
            System.out.println("7.Back");
            System.out.println("-------------------");
            this.choice = sc2.nextInt();
            System.out.println("--------------------");
            if (this.choice == 1) {
                new_member = new Member();
                new_member.regMem();
            } else if (this.choice == 2) {
                new_member = new Member();
                System.out.println("Choose an option:");
                System.out.println("1.Through member id: ");
                System.out.println("2.Through phone number: ");
                int opt = sc2.nextInt();
                if(opt == 1) {
                    System.out.println("Enter member id: ");
                    new_member.removeMem(sc2.nextInt());
                } else if (opt == 2){
                    new_member.removeMem();
                }
                else System.out.println("Incorrect option chosen.");
            } else if (this.choice == 3) {
                Book new_book = new Book();
                new_book.addBook();
            } else if (this.choice == 4) {
                Book new_book = new Book();
                new_book.removeBook();
            } else if (this.choice == 5){
                new_member = new Member();
                new_member.memberBookFine();
            }else if (this.choice == 6) {
                Book new_book = new Book();
                new_book.viewBooks();
            } else if (this.choice == 7) {
                bool_lib = false;
            } else {
                System.out.println("Choose a valid option");
            }
        }
    }
}

