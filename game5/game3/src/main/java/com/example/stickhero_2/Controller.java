package com.example.stickhero_2;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class Controller {
    @FXML
    private Line stick;
    @FXML
    private Button button;
    private MethodHandles scene;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Rectangle firstPillar;
    @FXML
    private ImageView hero;
    private Parent root;
    @FXML
    private Text scoreText;
    @FXML
    private Text highScore;

    private static boolean heroReachedonNextPillar = false;

    public static boolean isHeroReachedonNextPillar() {
        return heroReachedonNextPillar;
    }

    public static void setHeroReachedonNextPillar(boolean heroReachedonNextPillar) {
        Controller.heroReachedonNextPillar = heroReachedonNextPillar;
    }

    static Pillar p1 = new Pillar();
    static Stick s1 = new Stick();

    private static Controller controller = new Controller();

    public static Controller getController() {
        return controller;
    }

    public ImageView getHero() {
        return hero;
    }

    public Line getStick() {
        return stick;
    }

    public Button getButton() {
        return button;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void addPillar(AnchorPane anchorPane){
        if (anchorPane == null) {
            System.out.println("It is nullllll");
        }
        p1.generateNextPillar(anchorPane);
    }

    public double calculateLength(){
        Line stik = (Line) NewGame.scene.lookup("#stick");
        double length = Math.abs(stik.getEndY() - stik.getStartY());
        System.out.println("Stick Length : " + length);
        return length;

    }

    public void increaseStickLength(){
        Line stik = (Line) NewGame.scene.lookup("#stick");
        s1.increaseLength(button, stik);
    }

    public void openPausepage(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pause-page.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        javafx.scene.Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gameOverpage(Stage stage, RunningGame g) throws IOException {

        root = FXMLLoader.load(getClass().getResource("revive-page.fxml"));
        javafx.scene.Scene scene = new Scene(root);
        stage.setScene(scene);
        Text txtt = (Text) scene.lookup("#scoreText");
        String s = "Score : " + g.getGame().getScore();
        txtt.setText(s);
        Text high = (Text) scene.lookup("#highScore");
        if (g.getGame().getScore() > Hero.getHighscore()){
            String s1 = "Highest Score : " + g.getGame().getScore();
            Hero.setHighscore(g.getGame().getScore());
            high.setText(s1);
        }else{
            String s1 = "Highest Score : " + Hero.getHighscore();
            high.setText(s1);
        }




        stage.show();
        cleanup();
    }

    public void home(MouseEvent e) throws IOException{
        root = FXMLLoader.load(getClass().getResource("playpage.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        javafx.scene.Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void cleanup(){
        Stick.setStickVertical(true);
        NewGame.timelineNewGame.stop();
        Pillar.getPillarArrayList().clear();
        RunningGame.getRunningGameArraylist().clear();
    }

    public void retry(MouseEvent e) throws IOException{
      NewGame newGame = new NewGame();
      newGame.changeScreenToPlayPage(e);
    }

    public void updateScoreText(RunningGame g) throws IOException {
        Text txt = (Text) NewGame.scene.lookup("#scoreText");
//        Parent temp = FXMLLoader.load(getClass().getResource("revive-page.fxml"));
//        javafx.scene.Scene scene = new Scene(temp);
//        Text txtt = (Text) scene.lookup("#scoreText");
        String s = "Score : " + g.getGame().getScore();
        txt.setText(s);
//        txtt.setText(s);
    }


    public void flipHero(KeyEvent e){
        System.out.println("Inside flip hero............................................");
        System.out.println("Key Pressed: " + e.getCode());
        System.out.println("Character Pressed: " + e.getCharacter());;
        System.out.println(e.getCode().toString());
//        ImageView hero = (ImageView) NewGame.scene.lookup("#hero");

        if (e.getCharacter().equals(" ")){
            System.out.println("Inside flip hero if............................................");
            System.out.println("Before y = " + hero.getY());
            ImageView hero = (ImageView) NewGame.scene.lookup("#hero");
//            TranslateTransition down = new TranslateTransition();
//            down.setNode(hero);
//            down.setByY(hero.getY());
//            down.setDuration(Duration.millis(1));
//            down.play();
//            down.setOnFinished(event-> System.out.println("After y = " + hero.getY()));
//            hero.setY(hero.getY() - 150);
//            hero.setX(hero.getX() - 100);
            hero.setScaleY(-1);
        }

    }

}
