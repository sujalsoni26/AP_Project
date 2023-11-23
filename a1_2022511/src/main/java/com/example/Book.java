package com.example;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.Instant;

 import java.util.*;

public class Book {
    private static int bookID;
    private static int final_bookID;
    private String title;
    private String author;
    private int copies;
    private Instant starttime;
    private Instant endtime;
    private long bookfine;
    private long days;
    private static ArrayList<String> list_books = new ArrayList<>();
    private static HashMap<Integer, String> available_books = new HashMap<>();
    private static HashMap<Integer, String> ID_title = new HashMap<>();
    private static HashMap<String, String> title_author = new HashMap<>();
    private static HashMap<Integer, Instant> ID_start = new HashMap<>();
    private static HashMap<Integer,Instant> ID_end = new HashMap<>();
    private static HashMap<Integer,Long> bookID_fine = new HashMap<>();
    Scanner sc3 = new Scanner(System.in);

    public long getBookfine() {
        return bookfine;
    } 
    public long getDays() {
        return days;
    }
    public void setDays(long days) {
        this.days = days;
    }
    public void setBookfine(long bookfine) {
        this.bookfine = bookfine;
    } 
    public Instant getStarttime() {
        return starttime;
    }
    public void setStarttime(Instant starttime) {
        this.starttime = starttime;
    }
    public Instant getEndtime() {
        return endtime;
    }
    public void setEndtime(Instant endtime) {
        this.endtime = endtime;
    }
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        Book.bookID = bookID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public static int getFinal_bookID() {
        return final_bookID;
    }
    public static void setFinal_bookID(int final_bookID) {
        Book.final_bookID = final_bookID;
    }
    public void addBook() {
        System.out.print("Book title:");
        setTitle(sc3.nextLine());
        System.out.print("Author:");
        setAuthor(sc3.nextLine());
        System.out.print("Copies:");
        this.copies = sc3.nextInt();
        title_author.put(getTitle(), getAuthor());
        for (int i = 0; i < this.copies; i++) {
            setBookID(++bookID);
            setFinal_bookID(++final_bookID);
            ID_title.put(getFinal_bookID(), getTitle());
            available_books.put(getFinal_bookID(), getTitle());
            list_books.add(getTitle());
        }
        System.out.println("Book added successfully");
    }
    public void removeBook() {
        System.out.print("Book ID to be removed: ");
        setBookID(sc3.nextInt());
        if(ID_title.containsKey(getBookID())) {
            if (available_books.containsKey(getBookID())) {
                System.out.println("Book removed successfully");
            }else{
                System.out.println("Book unavailable to be removed.");
            }

        }else{
            System.out.println("Book does not exist.");
        }
        setTitle(ID_title.get(getBookID()));
        ID_title.remove(getBookID());
        available_books.remove(getBookID());
        list_books.remove(getTitle());
    }
    public void viewBooks() {
        for (int i = 1; i <= getFinal_bookID(); i++) {
            if (ID_title.containsKey(i)) {
                System.out.println("Book ID: " + i);
                setTitle(ID_title.get(i));
                System.out.println("Name: " + getTitle());
                System.out.println("Author: " + title_author.get(getTitle()));
                System.out.println();
            } else {
                continue;
            }

        }
    }
    public void availableBooks() {
        for (int i = 1; i <= list_books.size()+1; i++) {
            if (available_books.containsKey(i)) {
                System.out.println("Book Id:" + i);
                System.out.println("Title: " + available_books.get(i));
                System.out.println();
            } else {
                continue;
            }
        }
    }
    public void issueBooks() {
        if (available_books.containsKey(getBookID())) {
            available_books.remove(getBookID());
            setStarttime(Instant.now());
            ID_start.put(getBookID(), getStarttime());
        }
    }
    public void returnBooks() {
        setEndtime(Instant.now());
        ID_end.put(getBookID(), getEndtime());
        calculateFine(getBookID());
        available_books.put(getBookID(), ID_title.get(getBookID()));
    }
    public String getTitle(int book_ID) {
        return ID_title.get(book_ID);
    }
    public void myBook(ArrayList<Integer> mybook) {
        if (mybook.size()<=0){
            System.out.println("No books are issued.");
        }
        for (int i = 0; i <mybook.size() ; i++) {
            String namebook = ID_title.get(mybook.get(i));
            System.out.println("Book id: " + mybook.get(i));
            System.out.println("Book name: " + namebook);
        }
    }
    public boolean checkAvail(int bookID) {
        if (available_books.containsKey(bookID)) {
            return true;
        } else {
            return false;
        }
    }
    public long calculateFine(int bookID) {
        Duration day = Duration.between(ID_start.get(getBookID()),ID_end.get(getBookID()));
        if (day.getSeconds() > 10) {
            setDays(day.getSeconds()-10);
            setBookfine(3 * (days - 10));
        } else {
            setBookfine(0);
        }
        bookID_fine.put(bookID,getBookfine());
        return getBookfine();
    }
    public long getbookfine(int bookID){
        return bookID_fine.get(bookID);
    }
}
