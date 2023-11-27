package com.example.stickhero_2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
//    private MethodHandles scene;
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
    @FXML
    private Text cherryText;
    @FXML
    private AnchorPane gameOverAnchorPane;

    private static boolean heroReachedonNextPillar = false;
    private static boolean hasRevived = false;

    public static boolean isHasRevived() {
        return hasRevived;
    }

    public static void setHasRevived(boolean hasRevived) {
        Controller.hasRevived = hasRevived;
    }

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

    private static Scene scene;

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
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gameOverpage(Stage stage, RunningGame g) throws IOException {

        root = FXMLLoader.load(getClass().getResource("revive-page2.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);

        Text txtt = (Text) scene.lookup("#scoreText");
        String s = "Score : " + g.getGame().getScore();
        txtt.setText(s);

        Text cherry = (Text) scene.lookup("#cherryText");
        String s2 = "Cherries    : " + Hero.getCherries();
        cherry.setText(s2);

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
        RunningGame.getRunningGameArraylist().clear();
        root = FXMLLoader.load(getClass().getResource("playpage.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void cleanup(){
        Stick.setStickVertical(true);
        NewGame.timelineNewGame.stop();
        Pillar.getPillarArrayList().clear();
//        RunningGame.getRunningGameArraylist().clear();
        Hero.setFlip(false);
    }

    public void retry(MouseEvent e) throws IOException{
      RunningGame.getRunningGameArraylist().clear();
      NewGame newGame = new NewGame();
      newGame.changeScreenToPlayPage(e);
    }
    private Rectangle createRectangle(double x, double y, double width, double height, Color fill) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setArcWidth(5.0);
        rectangle.setArcHeight(5.0);
        rectangle.setFill(fill);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        return rectangle;
    }

    private Text createText(String content, double x, double y, double fontSize) {
        Text text = new Text(content);
        text.setX(x);
        text.setY(y);
        text.setFont(new Font(fontSize));
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        return text;
    }

    private ImageView createImageView(double x, double y, double xl, double yl) {
        ImageView imageView = new ImageView(new Image("imgbin_cherry-png.png"));
        imageView.setX(x);
        imageView.setY(y);
        imageView.setLayoutX(xl);
        imageView.setLayoutY(yl);
        imageView.setFitWidth(35.0);
        imageView.setFitHeight(34.0);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        return imageView;
    }
    public void revive(MouseEvent e){

        RunningGame g = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
        Rectangle rectangle1 = createRectangle(223.0, 166.0, 386.0, 267.0, Color.WHITE);
        Rectangle rectangle2 = createRectangle(263.0, 366.0, 119.0, 52.0, Color.valueOf("#27b16f"));
        Rectangle rectangle3 = createRectangle(443.0, 366.0, 119.0, 52.0, Color.DODGERBLUE);

        // Create Text elements
        Text textRevive = createText("Revive", 290.0, 400.0, 24.0);
        Text textBack = createText("Back", 478.0, 401.0, 24.0);
        Text textCurrentScore = createText("Current Score : " + g.getGame().getScore(), 324.0, 288.0, 24.0);
        Text textCherry = createText(": " + Hero.getCherries(), 427.0, 330.0, 24.0);
        Text textReviveFor5 = createText("Revive for 5", 301.0, 222.0, 36.0);

        // Create ImageViews
        ImageView cherry1 = createImageView(700.0, 95.0, -197, 96);
        ImageView cherry2 = createImageView(700.0, 95.0, -314, 209);
        AnchorPane ap = (AnchorPane) scene.lookup("#gameOverAnchorPane");
//        AnchorPane app = (AnchorPane) scene.lookup("#anchorPane");
        Group groupOfBack = new Group();
        Group groupOfRevive = new Group();
        groupOfBack.getChildren().addAll(rectangle3, textBack);
        groupOfRevive.getChildren().addAll(rectangle2, textRevive);
        // Add all elements to the AnchorPane
        ap.getChildren().addAll(rectangle1, groupOfBack,
                groupOfRevive, textCurrentScore, textCherry, textReviveFor5,
                cherry1, cherry2);



        groupOfRevive.setOnMouseClicked(mouseEvent -> {
            if (Hero.getCherries() < 5){
                Rectangle rectangle4 = createRectangle(277, 221, 252, 56, Color.BLACK);
                rectangle4.setOpacity(0.35);
                Text textNotEnoughCherry = createText("Not Enough Cherries", 277, 259, 26);
                textNotEnoughCherry.setFill(Color.WHITE);
                Group group = new Group(rectangle4, textNotEnoughCherry);


                Timeline timeline2 = new Timeline(new KeyFrame(Duration.ZERO, event -> {
                    ap.getChildren().removeAll(rectangle1, groupOfRevive, groupOfBack,
                            textCurrentScore, textCherry, textReviveFor5,
                            cherry1, cherry2);
//                    Group group = new Group(rectangle4, textNotEnoughCherry);
                    ap.getChildren().add(group);

                }), new KeyFrame(Duration.seconds(3), event -> {
                    ap.getChildren().remove(group);
                    ap.getChildren().addAll(rectangle1, groupOfBack,
                            groupOfRevive, textCurrentScore, textCherry, textReviveFor5,
                            cherry1, cherry2);
                        }));

                timeline2.setCycleCount(1);
                timeline2.play();

            }
            else {
                Hero.setCherries(Hero.getCherries() - 5);
                hasRevived = true;
                NewGame newGame = new NewGame();
                try {
                    newGame.changeScreenToPlayPage(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }

        });
        groupOfBack.setOnMouseClicked(mouseEvent -> {
            ap.getChildren().removeAll(rectangle1, groupOfRevive, groupOfBack,
                    textCurrentScore, textCherry, textReviveFor5,
                    cherry1, cherry2);
        });

    }

    public void updateScoreText(RunningGame g) throws IOException {
        Text txt = (Text) NewGame.scene.lookup("#scoreText");
        String s = "Score : " + g.getGame().getScore();
        txt.setText(s);
    }

    public void updateCherryText() throws IOException {
        Text txt = (Text) NewGame.scene.lookup("#cherryText");
        String s = "Cherries    : " + Hero.getCherries();
        txt.setText(s);
    }


    public void flipHero(KeyEvent e){
        if (e.getCharacter().equals(" ")){
            if (!Hero.isFlip()){
                hero.setY(hero.getY() + 35);
                hero.setScaleY(-1);
                Hero.setFlip(true);
            } else {
                hero.setY(hero.getY() - 35);
                hero.setScaleY(1);
                Hero.setFlip(false);
            }
        }

    }


}
