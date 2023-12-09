# AP_Project

# STICK HERO
# Advanced Programming Course Project 

**Group Number : 44**  

**Group Members:**  
**1.Suhani Kalyani- 2022511**   
**2.Sujal Soni - 2022513** 

## Assumptions
1.To ensure a smooth gaming experience, the initial press upon loading a new game is omitted, preventing unintended actions for the user.   
The stick starts to increase from the second press onward.  

2.The user will only save one game at a time.


## How to run the file?
To run the file, download the zip folder **2022511_2022513.zip**. The code is available in ***demo/src/main/java/com/example/stickhero_2***. There are 12 files , the main file being  **HelloApplication.java** , running which will launch the game.

## Functionalities
1. Increasing stick
2. Horizontal stick
3. Hero walk
4. Shift Pillars
5. Save game
6. Load Game
7. Sound
8. Pause Game
9. Resume Game
10. Revive 
11. Go to Home page
12. Retry
13. Exit
14. Collect cherries



## OOP concepts applied
- ### Encapsulation  
    -   **Private**  
    The variables/attributes created are all private.
    ```java
    private String name;
    private Timeline timelineHero;
    private static int highscore = 0;
    private static int cherries = 0;
    private Controller controller;
    ```
    -  **Accessor and Mutator**  
    To access those variables I have used **getter and setter** to use them.  
    For instance , in Visitor class :
    ```java
    public int getScore() {
    return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    ```
- ### Classes
    Multiple classes are created for better reusability of the code.  
    Few of them are-  
    - Pillar
    - Hero
    - Controller
    - NewGame
    - Cherries
    - Stick
    
- ### Public Methods  
    The methods within the code are made public.   
    For example - 
    ```java
    public void moveHero();
    public void shiftPillar()
    ```  
- ### Objects 
    To access methods of a different class , objects are created.  
    ```java
    Pillar p1 = new Pillar();
    p1.generateNextPillar(anchorPane);
    ```
- ### Inheritance
    RunningGame extends SavedGame.
    ```java
    public class RunningGame extends SavedGame{}
    ```
- ### Interface
    A Game interface is created and Saved Game and New Game implements this interface.  
    Polymorphism is also used.
    ```java
    public interface Game{}
    ```


## Serialization and Deserialization
Serializable is implemented to save the game and deserialization is used to load the game.
E

## JUnit Test
Junit Test is run in HelloApplication main function before launch();
It checks 4 functions and the test code is written in TestClass. According to the test written , it should be true in all 4 cases.
It tests for the calculateDistance function in Pillar class, isFlip function in hero class , randomNumber in in Cherries and subCherries in Hero class.

## Sound
Sound and other graphics and animations are added to enhance the user experience.

## Design Patterns
- ### Iterator  
Iterator design pattern is used in Running class and is used to access the ArrayList of running game.
```java
Iterator<Game> iterator = RunningGame.getRunningGameIterator();
RunningGame g = null;
while (iterator.hasNext()) {
    g = (RunningGame) iterator.next();
}
```

- ### Factory Method
Functions like createNewStick are present.

- ### Model-View Controller
There is a controller class which handles the events(mouse click) and executes appropriate functions.

## Note 
Threads are also implemented in Controller class.
Link to the github repository - https://github.com/sujalsoni26/AP_Project.git

