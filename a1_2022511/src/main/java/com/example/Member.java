package com.example;

import java.util.*;

public class Member extends Book {
    private static int final_memberID;
    private int member_ID;
    private long tot_fine;
    Scanner s4 = new Scanner(System.in);
    private boolean bool = true;
    private int choice;
    private String name;
    private long phone_no;
    private int age;
    private static ArrayList<String> members = new ArrayList<>();
    private static HashMap<Long, String> phone_member = new HashMap<>();
    private static HashMap<Integer, String> ID_name = new HashMap<>();
    private static HashMap<String, Long> name_phone = new HashMap<>();
    private static HashMap<String, Integer> name_ID = new HashMap<>();
    private static HashMap<String, ArrayList<Integer>> listb = new HashMap<>();
    private static HashMap<Integer, Long> memID_fine = new HashMap<>();

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getPhone_no() {
        return phone_no;
    }
    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }
    public int getMember_ID() {
        return member_ID;
    }
    public void setMember_ID(int member_ID) {
        this.member_ID = member_ID;
    }
    public static int getFinal_memberID() {
        return final_memberID;
    }
    public static void setFinal_memberID(int final_memberID) {
        Member.final_memberID = final_memberID;
    }
    public long getTot_fine() {
        return tot_fine;
    }
    public void setTot_fine(long tot_fine) {
        this.tot_fine = tot_fine;
    }
    private void payFine(){
        try{
            if (memID_fine.get(getMember_ID()) > 0) {
                System.out.println("You had a total fine of Rs " + memID_fine.get(getMember_ID()) + ".It has been paid successfully");
                long fine = 0;
                memID_fine.replace(getMember_ID(), fine);
            }else{
                System.out.println("No fine due.");
            }
        }catch (Exception e){
            System.out.println("No fine due.");
                }
    }
    public void enterFine() {
        Book new_book = new Book();
        long tot = 0;
        ArrayList<Integer> il = listb.get(getName());
        if ((il != null) || !il.isEmpty()) {
            for (int i = 0; i < il.size(); i++) {
                int index = i;
                tot += calculateFine(il.get(index));
                setTot_fine(tot);
            }
        } else {
            setTot_fine(0);
        }
        if(memID_fine.containsKey(getMember_ID())){
            memID_fine.replace(getMember_ID(),getTot_fine());
        }
        memID_fine.put(getMember_ID(),getTot_fine());
    }
    public void enter() {
        System.out.print("Name: ");
        setName(s4.nextLine());
        System.out.print("Phone number: ");
        setPhone_no(s4.nextLong());
        setMember_ID(name_ID.get(getName()));
        if (phone_member.containsKey(this.phone_no) && name_phone.get(getName())==getPhone_no()) {
            System.out.println("Welcome " + getName() + " Member Id: " + getMember_ID());
            menu();
        } else {
            System.out.println("Member with name " + getName() + " and phone no. " + getPhone_no() + " does not exist.");
            System.out.println("----------------------");
        }
    }
    public void regMem() {
        System.out.print("Name: ");
        setName(s4.nextLine());
        System.out.print("Age: ");
        try {
            setAge(s4.nextInt());
            
        } catch (Exception e) {
            System.out.println("Please enter integer value in age.");
        }
        s4.nextLine();
        System.out.print("Phone no.(10 number digits): ");
        long  num = s4.nextLong();
        String n = Long.toString(num);
        if (n.length()==10) {
            setPhone_no(num);
            if (phone_member.containsKey(getPhone_no())) {
                System.out.println("Member with this phone number already exists.");
            } else {
                setMember_ID(++member_ID);
                setFinal_memberID(++final_memberID);
                members.add(getName());
                phone_member.put(getPhone_no(), getName());
                ID_name.put(getFinal_memberID(), getName());
                name_phone.put(getName(), getPhone_no());
                name_ID.put(getName(), getFinal_memberID());
                listb.put(getName(), null);
                System.out.println("---------------------------------------------");
                System.out.println("Member Successfully registered with Member Id " + getFinal_memberID());
            }
        }else{
            System.out.println("Invalid phone number.Please enter 10 digit number.");
        }
    }
    public void removeMem(int member_ID) {
        if(ID_name.containsKey(member_ID)){
            System.out.println("--------------------------");
            System.out.println("Member removed successfully");
            System.out.println("----------------------------");
            setMember_ID(member_ID);
            setName(ID_name.get(member_ID));
            ID_name.remove(getMember_ID());
            members.remove(getName());
            setPhone_no(name_phone.get(getName()));
            phone_member.remove(getPhone_no());
            name_phone.remove(getName());
            listb.remove(getName());
        }else{
            System.out.println("Member does not exist.");
        }
    }
    public void removeMem() {
        System.out.print("Enter phone no. : ");
        setPhone_no(s4.nextLong());
        if(phone_member.containsKey(getPhone_no())) {
            setName(phone_member.get(getPhone_no()));
            phone_member.remove(getPhone_no());
            name_phone.remove(getName());
            members.remove(getName());
            setMember_ID(name_ID.get(getName()));
            name_ID.remove(getName());
            ID_name.remove(getMember_ID());
            listb.remove(getName());
            System.out.println("Member removed successfully");
        }else{
            System.out.println("Member does not exist");
        }
    }
    public void memberBookFine() {
        Set<String> nameofmem = listb.keySet();
        for (String key : nameofmem) {
            System.out.println("Member name: " + key);
            setName(key);
            setMember_ID(name_ID.get(key));
            if(listb.containsKey(key)) {
                if (listb.get(key) != null) {
                    setMember_ID(name_ID.get(key));
                    ArrayList<Integer> bookIDlist;
                    bookIDlist = listb.get(key);
                    myBook(bookIDlist);
                } else {
                    System.out.println("No books are issued.");
                }
            }else{
                System.out.println("No books are issued currently.");
            }
            try {
                if (memID_fine.containsKey(getMember_ID())) {
                    enterFine();
                    System.out.println("Fine: " + memID_fine.get(getMember_ID()));
                } else {
                    System.out.println("No fine is due.");
                }
            }catch (Exception e ){
                System.out.println("Fine will be calculated once the book is returned.");
            }
            System.out.println();
        }
    }
    public void menu() {
        while (bool) {
            System.out.println("-----------------");
            System.out.println("1.List available Books");
            System.out.println("2.List My Books");
            System.out.println("3.Issue book");
            System.out.println("4.Return book");
            System.out.println("5.Pay Fine");
            System.out.println("6.Back");
            System.out.println("-----------------");
            this.choice = s4.nextInt();
            if (this.choice == 1) {
                Book list = new Book();
                list.availableBooks();
            } else if (this.choice == 2) {
                Book list = new Book();
                try {
                    list.myBook(listb.get(getName()));
                } catch (Exception e) {
                    System.out.println("No book has been issued.");
                }
            } else if (this.choice == 3) {
                ArrayList<Integer> innerlist;
                if (listb.get(getName()) == null) {
                    innerlist = new ArrayList<>();
                    listb.put(getName(), innerlist);
                } else {
                    innerlist = listb.get(getName());
                }
                enterFine();
                if (memID_fine.containsKey(getMember_ID())) {
                    if (memID_fine.get(getMember_ID()) > 0) {
                        System.out.println("Please pay the fine first.");
                    } else {
                        int numbooks = listb.get(getName()).size();
                        if (numbooks < 2) {
                            System.out.print("Book ID: ");
                            setBookID(s4.nextInt());
                            if (checkAvail(getBookID())) {
                                issueBooks();
                                System.out.println("-----------------------");
                                System.out.println("Book Issued successfully");
                                System.out.println("------------------------");
                                innerlist.add(getBookID());
                                listb.replace(getName(), innerlist);} else {
                                System.out.println("Book unavailable.");
                            }
                        } else {
                            System.out.println("Cannot issue more than 2 books.");
                        }
                    }
                }
            }else if (this.choice == 4) {
                    System.out.print("Enter Book ID : ");
                    setBookID(s4.nextInt());
                    if(checkAvail(getBookID())){
                        System.out.println("This book cannot be returned as it is already available.");
                    }
                    else {
                        returnBooks();
                        enterFine();
                        ArrayList<Integer> innerlist;
                        if (calculateFine(getBookID()) > 0) {
                            System.out.println("Book ID: " + getBookID() + " successfully returned. ");
                            System.out.println(calculateFine(getBookID()) + " Rupees has been charged for a delay of " + (getDays() - 10) + " days.");
                        } else {
                            System.out.println("Book ID: " + getBookID() + " successfully returned. ");
                        }
                        innerlist = listb.get(getName());
                        int indexofbook = innerlist.indexOf(getBookID());
                        innerlist.remove(indexofbook);
                        listb.replace(getName(),innerlist);

                    }

                } else if (this.choice == 5) {
                    payFine();
                    // try{
                    //     if (memID_fine.get(getMember_ID()) > 0) {
                    //         System.out.println("You had a total fine of Rs " + memID_fine.get(getMember_ID()) + ".It has been paid successfully");
                    //         long fine = 0;
                    //         memID_fine.replace(getMember_ID(), fine);
                    //     }else{
                    //         System.out.println("No fine due.");
                    //     }
                    // }catch (Exception e){
                    //     System.out.println("No fine due.");
                    // }
                } else if (this.choice == 6) {
                    bool = false;
                } else {
                    System.out.println("Choose a valid option.");
                }
            }
        }
    }