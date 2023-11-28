package com.example.stickhero_2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class NewGame implements Game {
    @FXML
    private Text textLoadGame;
    @FXML
    private Ellipse circleLoadGame;
    static Parent root;
    static Timeline timelineNewGame = new Timeline();
    static Scene scene;

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
        this.saveGame();
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

    Ellipse newGameEllipse;
    Text newGameText;

    public void changeScreenToPlayPage(MouseEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("running-game-scene.fxml"));
        Parent root = loader.load();

        // Get the controller instance
        Controller controller = loader.getController();

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        AnchorPane anchorPane = (AnchorPane) scene.lookup("#anchorPane");
        stage.setScene(scene);

        scene.setOnKeyTyped(controller::flipHero);
        Cherries c1 = new Cherries();

        Text cherry = (Text) scene.lookup("#cherryText");
        String s2 = "Cherries    : " + Hero.getCherries();
        cherry.setText(s2);

        if (Controller.isHasRevived()){
            Controller.setHasRevived(false);
            RunningGame g = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
            Text score = (Text) scene.lookup("#scoreText");
            String s3 = "Score : " + g.getGame().getScore();
            score.setText(s3);
        } else if (isLoaded) {
            isLoaded = false;
            System.out.println("Inside newGame if ");
//            Game previousGame = Controller.getController().loadGame(e);
            Game previousGame = loadPreviousGame((RunningGame) SavedGame.getSavedgames().get(1));
            NewGame g = (NewGame) previousGame;
            RunningGame g1 = new RunningGame(g, createHero());


//            Game runningGame = new RunningGame((NewGame) previousGame, createHero());
//            RunningGame g = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
            Text score = (Text) scene.lookup("#scoreText");
            String s3 = "Score : " + g1.getGame().getScore();
            score.setText(s3);

        } else{
            Game newGame = createNewGame();
            Game runningGame = new RunningGame((NewGame) newGame, createHero());
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

    public void load(MouseEvent e) throws IOException {
        System.out.println("Inside Load");
        isLoaded = true;
        Controller.getController().loadGame(e);
    }
}
