package com.example.stickhero_2;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

public class NewGame implements Game, Serializable, Runnable {
    @FXML
    private Text textLoadGame;
    @FXML
    private Text stickHeroText;
    @FXML
    private Ellipse circleLoadGame;
    @FXML
    private AnchorPane mainPageAnchorPane;
    @FXML
    private Text exitText;
    @FXML
    private Ellipse exitEllipse;
    @FXML
    private Text t2;

    static Parent root;
    static Timeline timelineNewGame = new Timeline();
    static Scene scene;

    public AnchorPane getMainPageAnchorPane() {
        return mainPageAnchorPane;
    }

    private static boolean isLoaded = false;

    public static boolean isIsLoaded() {
        return isLoaded;
    }

    public static void setIsLoaded(boolean isLoaded) {
        NewGame.isLoaded = isLoaded;
    }

    public Text getTextLoadGame() {
        return textLoadGame;
    }

    public Ellipse getCircleLoadGame() {
        return circleLoadGame;
    }

    public void saveGame() {
        SavedGame.getSavedgames().add(this);
    }

    @Override
    public Hero createHero() {
        return new Hero("Stickman");
    }

    public NewGame(String x){

    }
    public NewGame(){

    }
    public NewGame(int x) {
        this.score = x;
        this.currentlyActive = true;
        this.hasEnded = false;
        this.savedIndex = 0;
//        this.saveGame();
    }

    public Game createNewGame(){

        return new NewGame(0);
    }
    public Game loadPreviousGame(RunningGame g){
        return new NewGame(g.getGame().getScore());
    }

    private int score;
    private boolean currentlyActive;
    private boolean hasEnded;
    private int savedIndex;


    public int getSavedIndex() {
        return savedIndex;
    }

    public void setSavedIndex(int savedIndex) {
        this.savedIndex = savedIndex;
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public boolean isCurrentlyActive() {
        return currentlyActive;
    }

    public void setCurrentlyActive(boolean currentlyActive) {
        this.currentlyActive = currentlyActive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private boolean firstpillarflag = true;


    public void changeScreenToPlayPage(MouseEvent e) throws IOException, ClassNotFoundException {



        FXMLLoader loader = new FXMLLoader(getClass().getResource("running-game-scene.fxml"));
        Parent root = loader.load();

        // Get the controller instance
        Controller controller = loader.getController();

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        AnchorPane anchorPane = (AnchorPane) scene.lookup("#anchorPane");
        stage.setScene(scene);
        Controller.getController().changeCursor();
        scene.setOnKeyTyped(controller::flipHero);
        Cherries c1 = new Cherries();
        if (HelloApplication.getMediaPlayer() != null){
            HelloApplication.getMediaPlayer().stop();
        }
        Media media = new Media(new File("src\\main\\resources\\bgmusic.mp3").toURI().toString());
        HelloApplication.setMediaPlayer(new MediaPlayer(media));
        HelloApplication.getMediaPlayer().play();

        SavedGame sg = null;
        try {
            FileInputStream fis2 = new FileInputStream("saveScore.txt");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);

            sg = (SavedGame)ois2.readObject();
            ois2.close();fis2.close();
        }catch (FileNotFoundException exception){
            System.out.println("Inside catch");

        }

        if (sg != null){
            System.out.println("........Inside If......");
            Hero.setCherries(sg.getCherries());
            if (Hero.getHighscore() < sg.getHighScore()){
                Hero.setHighscore(sg.getHighScore());

            }

        }


        if (Controller.isHasRevived()){
            Controller.setHasRevived(false);
            Hero.setCherries(Hero.getCherries() - 5);
            Iterator<Game> iterator = RunningGame.getRunningGameIterator();
//            Iterator<Game> iterator = RunningGame.getRunningGameIterator();
            RunningGame g = null;
            while (iterator.hasNext()) {
                g = (RunningGame) iterator.next();
            }

//            RunningGame g = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
            Text score = (Text) scene.lookup("#scoreText");
            String s3 = "Score : " + g.getGame().getScore();
            score.setText(s3);
            Text cherry = (Text) scene.lookup("#cherryText");
            String s2 = "Cherries    : " + Hero.getCherries();
            cherry.setText(s2);
        } else if (isLoaded) {
            isLoaded = false;
            System.out.println("Inside newGame if ");
//            Game previousGame = Controller.getController().loadGame(e);
            FileInputStream fis1 = new FileInputStream("saveGame.txt");
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            RunningGame loadedGame = (RunningGame) ois1.readObject();
            ois1.close();fis1.close();
            if (loadedGame != null){


//                Game previousGame = loadPreviousGame((RunningGame) SavedGame.getSavedgames().get(0));
                Game previousGame = loadPreviousGame(loadedGame);
                SavedGame.getSavedgames().clear();
                NewGame g = (NewGame) previousGame;
//            g.saveGame();
                RunningGame g1 = new RunningGame(g, createHero());

//            Game runningGame = new RunningGame((NewGame) previousGame, createHero());
//            RunningGame g = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
                Text score = (Text) scene.lookup("#scoreText");
                String s3 = "Score : " + g1.getGame().getScore();
                score.setText(s3);
                Text cherry = (Text) scene.lookup("#cherryText");
                String s2 = "Cherries    : " + Hero.getCherries();
                cherry.setText(s2);


            }

//            else {
////                return;
//
//            }


        } else{
            Game newGame = createNewGame();
            Game runningGame = new RunningGame((NewGame) newGame, createHero());
            Text cherry = (Text) scene.lookup("#cherryText");
            String s2 = "Cherries    : " + Hero.getCherries();
            cherry.setText(s2);
        }


//        Controller.setHeroReachedonNextPillar(true);

        Rectangle pill = (Rectangle) NewGame.scene.lookup("#firstPillar");
        Pillar p1 = new Pillar(pill, 0);
        Pillar.getPillarArrayList().add(p1);

        timelineNewGame = new Timeline(
                new KeyFrame(Duration.millis(5), event -> {
                    if ((Controller.isHeroReachedonNextPillar()) || firstpillarflag) {
                        firstpillarflag = false;
//                    Controller.getController().addPillar(anchorPane);
                        controller.addPillar(anchorPane);
                        Rectangle p = (Rectangle) NewGame.scene.lookup("#secondPillar");
                        Button b1 = (Button) NewGame.scene.lookup("#button");
                        ImageView img = (ImageView) NewGame.scene.lookup("#pauseImage");
                        if (p != null){
                            System.out.println("P is NOT null---------");
                            c1.generateCherries(p);
                        }else{
                            System.out.println("P is null---------");
                        }
                        b1.toFront();
                        img.toFront();

                        Controller.setHeroReachedonNextPillar(false);
                    }
                })
        );
        timelineNewGame.setCycleCount(Timeline.INDEFINITE);
        timelineNewGame.play();
        stage.show();
    }

    @FXML
    public void load(MouseEvent e) throws IOException, ClassNotFoundException {
        FileInputStream fis1 = null;
        ObjectInputStream ois1 = null;
        try {
            fis1 = new FileInputStream("saveGame.txt");
            ois1 = new ObjectInputStream(fis1);
//            ois1 = new ObjectInputStream(fis1);
        }catch (FileNotFoundException exception){
            System.out.println("File not found");
            Timeline timeline2 = new Timeline(
                    new KeyFrame(Duration.millis(0), event -> {

                        t2.setVisible(true);
                    }), new KeyFrame(Duration.seconds(3), event -> {
                System.out.println("Removing");
                t2.setVisible(false);
            }));
            timeline2.setCycleCount(1);
            timeline2.play();
            return;
        }
//        FileInputStream fis1 = new FileInputStream("saveGame.txt");

        RunningGame loadedGame = null;
        try {
            loadedGame = (RunningGame) ois1.readObject();
        }catch (RuntimeException exception){
//            exception.getMessage();
            System.out.println("File not found");
            Timeline timeline2 = new Timeline(
                    new KeyFrame(Duration.millis(0), event -> {

                        t2.setVisible(true);
                    }), new KeyFrame(Duration.seconds(3), event -> {
                System.out.println("Removing");
                t2.setVisible(false);
            }));
            timeline2.setCycleCount(1);
            timeline2.play();
            return;
        }

        ois1.close();fis1.close();

        System.out.println("Inside Load");
        isLoaded = true;
        changeScreenToPlayPage(e);
    }
    public void exit(MouseEvent e) throws IOException {
        FileOutputStream fos1 = new FileOutputStream(new File("saveScore.txt"));
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);

        SavedGame saveScore = new SavedGame();

        oos1.writeObject(saveScore);
        oos1.close();fos1.close();
        Platform.exit();
    }


    @Override
    public void run() {
        if (HelloApplication.getMediaPlayer() != null){
            HelloApplication.getMediaPlayer().stop();
        }
        Media media = new Media(new File("src\\main\\resources\\bgmusic.mp3").toURI().toString());
        HelloApplication.setMediaPlayer(new MediaPlayer(media));
        HelloApplication.getMediaPlayer().play();
    }
}
