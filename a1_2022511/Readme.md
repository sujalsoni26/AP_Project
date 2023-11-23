# Assignment 1 

## How to run the file?
To run the file, download the zip folder **a1_2022511.zip**. The code is available in ***a1_2022511/src/main/java/com/example***. There are 4 files , the main file being  **App** .

To run from the terminal type the following 2 commands one by one -  
**1.  mvn clean install**   
**2. java -jar target/a1_2022511-1.1.jar**  

This will automatically start the program.

## OOP concepts applied
- ### Encapsulation  
    -   **Private**  
    The variables/attributes created are all private.
    ```java
    private static int bookID;
    private static int final_bookID;
    private String title;
    private String author;
    private int copies;
    private Instant starttime;
    private Instant endtime;
    private long bookfine;
    private long days;
    ```
    -  **Accessor and Mutator**  
    To access those variables I have used **getter and setter** to use them.  
    For instance , in Member class :
    ```java
     public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    ```    
    
- ### Classes
    4 classes are created for better reusability of the code.  
    Classes are -  
    - App
    - Librarian
    - Book
    - Member

- ### Public Methods  
    The methods within the code are made public for the user to allow easy access to them .   
    For example - 
    ```java
    public void addBook();
    public void removeBook();
    ```  

    Note - The payFine() method is a private method since only a member from the Member class can access them.  

- ### Objects 
    To access methods of a different class , objects are created.  
    For example , when librarian wants to register a member , it has to create an object of Member class to access the regMem() function by :
    ```java
    private Member new_member;
    new_member = new Member();
    new_member.regMem();
    ```
   

- ### Class relationships
    - Librarian class has a dependency on Member class and Book class.
    - App class has association(has a) with Librarian class or Member class. Since to enter further in the program , the App class has to create a new Librarian() or a new Member().   
    - Member class has composition of Book class since members can have many books.

    Note- Method Overloading has also been applied since the user can remove a member either through bookID or through phone number.   


## Explanation of the code
The 4 files contain 4 different classes , namely  

1.App (it is the main class)  
2.Librarian  
3.Book  
4.Member

The App class provides the user the option to use the interface as a librarian or as a member.  

If the user chooses option 1 it enters the program as a librarian and the code goes into the Librarian class. Here the user gets all the functions from which it can choose the function they decide to perform.   

If the user chooses option 2 it enters the program as a member and the code goes into the Member class. Here again the user gets all the functions from which it can choose the function they decide to perform.
