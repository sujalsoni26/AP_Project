package com.example.stickhero_2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.lang.invoke.MethodHandles;

public class Controller implements Serializable {
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
    @FXML
    private ImageView pauseImage;
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

    private SavedGame savedGame = new SavedGame();

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

    private NewGame n1 = new NewGame("s");

    Hero h1 = new Hero();


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

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        Controller.scene = scene;
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


    public void gameOverpage(Stage stage, RunningGame g) throws IOException, ClassNotFoundException {

        root = FXMLLoader.load(getClass().getResource("revive-page2.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);

        if (HelloApplication.getMediaPlayer() != null){
            HelloApplication.getMediaPlayer().stop();
        }
        Media media = new Media(new File("src\\main\\resources\\bgmusic.mp3").toURI().toString());
        HelloApplication.setMediaPlayer(new MediaPlayer(media));
        HelloApplication.getMediaPlayer().play();

        FileInputStream fis2 = new FileInputStream("saveScore.txt");
        ObjectInputStream ois2 = new ObjectInputStream(fis2);

        SavedGame sg = (SavedGame)ois2.readObject();
        ois2.close();fis2.close();

        if (sg != null){
//            Hero.setCherries(sg.getCherries());
            if (Hero.getHighscore() < sg.getCherries()){
                Hero.setHighscore(sg.getHighScore());
            }

        }

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
        Image customCursorImage = new Image("cursor1.png");

        Cursor customCursor = new ImageCursor(customCursorImage);
        scene.setCursor(customCursor);
        cleanup();
    }

    public void home(MouseEvent e) throws IOException{
        RunningGame.getRunningGameArraylist().clear();
        root = FXMLLoader.load(getClass().getResource("playpage.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        if (HelloApplication.getMediaPlayer() != null){
            HelloApplication.getMediaPlayer().stop();
        }
        Media media = new Media(new File("src\\main\\resources\\bgmusic.mp3").toURI().toString());
        HelloApplication.setMediaPlayer(new MediaPlayer(media));
        HelloApplication.getMediaPlayer().play();
        Image customCursorImage = new Image("cursor1.png");

        Cursor customCursor = new ImageCursor(customCursorImage);
        scene.setCursor(customCursor);
//        HelloApplication.animations(((Node) e.getSource()).getScene());
        stage.show();
    }

    public void cleanup(){
        Stick.setStickVertical(true);
        NewGame.timelineNewGame.stop();
        Pillar.getPillarArrayList().clear();
//        SavedGame.getSavedgames().remove(0);
//        RunningGame.getRunningGameArraylist().clear();
        Hero.setFlip(false);
    }

    public void retry(MouseEvent e) throws IOException, ClassNotFoundException {
      RunningGame.getRunningGameArraylist().clear();
      NewGame newGame = new NewGame(0);
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
                NewGame newGame = new NewGame(0);
                try {
                    newGame.changeScreenToPlayPage(e);
                } catch (IOException | ClassNotFoundException ex) {
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
            if (!Hero.isFlip() && Hero.isHeroMoving()){
                hero.setY(hero.getY() + 35);
                hero.setScaleY(-1);
                Hero.setFlip(true);
            } else if (!Hero.isHeroMoving()) {
                return;
            } else{
                hero.setY(hero.getY() - 35);
                hero.setScaleY(1);
                Hero.setFlip(false);
            }
        }

    }

    public void pauseMenu(MouseEvent e){

        RunningGame g = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
        ImageView backgroundImage = new ImageView(new Image("pastel-mountains-vector-art-05xdup4f0zu2tvqa.jpg"));
        backgroundImage.setFitWidth(836);
        backgroundImage.setFitHeight(614);
        backgroundImage.setX(-2);
        backgroundImage.setY(-7);

        Lighting lighting = new Lighting();
        Light.Distant light = new Light.Distant();
        light.setAzimuth(45.0);
        light.setElevation(45.0);
        lighting.setLight(light);
        lighting.setBumpInput(null);
        lighting.setSpecularConstant(0.28);
        backgroundImage.setEffect(lighting);

        Text pausedText = new Text("PAUSED");
        pausedText.setFont(new Font(70));
        pausedText.setX(297);
        pausedText.setY(96);
//        pausedText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
//        pausedText.setStrokeWidth(0);
        pausedText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        pausedText.setWrappingWidth(271.12892150878906);

        ImageView saveButton = new ImageView(new Image(("save2.png")));
        saveButton.setFitWidth(85);
        saveButton.setFitHeight(84);
        saveButton.setX(550);
        saveButton.setY(343);

        Text saveText = new Text("Save");
        saveText.setFont(new Font(24));
        saveText.setX(567);
        saveText.setY(453);

        ImageView resumeButton = new ImageView(new Image(("resume2.png")));
        resumeButton.setFitWidth(85);
        resumeButton.setFitHeight(84);
        resumeButton.setX(213);
        resumeButton.setY(343);

        Rectangle temp = createRectangle(213, 343, 85, 84, Color.YELLOW);
        temp.setOpacity(0);

        Text resumeText = new Text("Resume");
        resumeText.setFont(new Font(24));
        resumeText.setX(213);
        resumeText.setY(453);

        Text textCurrentScore = createText("Current Score : " + g.getGame().getScore(), 329.0, 176.0, 24.0);
        Text textCherry = createText("Cherries   : " + Hero.getCherries(), 347.0, 208.0, 24.0);
        Text textHighScore = createText("High Score : " + Hero.getHighscore(), 364.0, 243.0, 24.0);
        ImageView cherry1 = createImageView(440.0, 187.0, 0, 0);

        Group group = new Group(temp, resumeButton, resumeText);
        Group group2 = new Group(saveText, saveButton);

        if (NewGame.timelineNewGame!= null){
            NewGame.timelineNewGame.pause();
            System.out.println("1. " + NewGame.timelineNewGame.getStatus());
        }
        else {
            System.out.println(";;;;;;;;;;;;;");
        }
//        if (Pillar.getTimelinePillar() != null){
//            Pillar.getTimelinePillar().pause();
//            System.out.println("2. " + Pillar.getTimelinePillar().getStatus());
//        }
//        else {
//            System.out.println("2 is null");
//        }
        if (Hero.getHeroParallelTransition() != null){
            Hero.getHeroParallelTransition().pause();
            System.out.println("3. " + Hero.getHeroParallelTransition().getStatus());
        }
        else {
            System.out.println("walk is null");
        }
        if (Hero.getCollisionTimer() != null){
            Hero.getCollisionTimer().stop();
//            System.out.println("4. " + h1.getCollisionTimer());
        }
        if (Pillar.getParallelTransition() != null){
            Pillar.getParallelTransition().pause();
            System.out.println("4. " + Pillar.getParallelTransition().getStatus());
        }
        else {
            System.out.println("4 is null");
        }
        if (Hero.getFall() != null){
            Hero.getFall().pause();
            System.out.println("5. " + Hero.getFall().getStatus());
        }else {
            System.out.println("5 is null");
        }
        if (Stick.getTimelineHorizontal() != null){
            Stick.getTimelineHorizontal().pause();
            System.out.println("6. " + Stick.getTimelineHorizontal().getStatus());
        }
        else {
            System.out.println("6 is null");
        }
        if (Pillar.getMoveHero1() != null){
            Pillar.getMoveHero1().pause();
            System.out.println("7. " + Pillar.getMoveHero1().getStatus());
        }
        else {
            System.out.println("7 is null");
        }
        anchorPane.getChildren().addAll(backgroundImage, pausedText, group2, group, textCherry, textHighScore, textCurrentScore, cherry1);

        group.setOnMouseClicked(mouseEvent -> {resume(backgroundImage, pausedText, group2, group, textCherry, textHighScore, textCurrentScore, cherry1);});
        group2.setOnMouseClicked(mouseEvent -> {
            try {
                savedGame.saveGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void resume(ImageView i1, Text t1, Group g2, Group g1, Text t4, Text t5, Text t6, ImageView i4){
        anchorPane.getChildren().removeAll(i1, i4, t1, g2, g1, t4, t5, t6);

        if (NewGame.timelineNewGame!= null && NewGame.timelineNewGame.getStatus() == Animation.Status.PAUSED){
            NewGame.timelineNewGame.play();
            System.out.println("1. " + NewGame.timelineNewGame.getStatus());
        }
        else {
            System.out.println("1. is null");
        }
//        if (Pillar.getTimelinePillar() != null){
//            Pillar.getTimelinePillar().pause();
//            System.out.println("2. " + Pillar.getTimelinePillar().getStatus());
//        }
//        else {
//            System.out.println("2 is null");
//        }
        if (Hero.getHeroParallelTransition() != null && Hero.getHeroParallelTransition().getStatus() == Animation.Status.PAUSED){
            Hero.getHeroParallelTransition().play();
            System.out.println("3. " + Hero.getHeroParallelTransition().getStatus());
        }
        else {
            System.out.println("walk is null");
        }
        if (Hero.getCollisionTimer() != null){
            Hero.getCollisionTimer().start();
//            System.out.println("4. " + h1.getCollisionTimer());
        }
        if (Pillar.getParallelTransition() != null && Pillar.getParallelTransition().getStatus() == Animation.Status.PAUSED){
            Pillar.getParallelTransition().play();
            System.out.println("4. " + Pillar.getParallelTransition().getStatus());
        }
        else {
            System.out.println("4 is null");
        }
        if (Hero.getFall() != null && Hero.getFall().getStatus() == Animation.Status.PAUSED){
            Hero.getFall().play();
            System.out.println("5. " + Hero.getFall().getStatus());
        }else {
            System.out.println("5 is null");
        }
        if (Stick.getTimelineHorizontal() != null && Stick.getTimelineHorizontal().getStatus() == Animation.Status.PAUSED){
            Stick.getTimelineHorizontal().play();
            System.out.println("6. " + Stick.getTimelineHorizontal().getStatus());
        }
        else {
            System.out.println("6 is null");
        }
        if (Pillar.getMoveHero1() != null && Pillar.getMoveHero1().getStatus() == Animation.Status.PAUSED){
            Pillar.getMoveHero1().play();
            System.out.println("7. " + Pillar.getMoveHero1().getStatus());
        }
        else {
            System.out.println("7 is null");
        }
    }

    public void changeCursor(){
        Image customCursorImage = new Image("cursor1.png");

        Cursor customCursor = new ImageCursor(customCursorImage);
        NewGame.scene.setCursor(customCursor);
        Button b1 = (Button) NewGame.scene.lookup("#button");
        ImageView i1 = (ImageView) NewGame.scene.lookup("#pauseImage");

        b1.setCursor(customCursor);
        i1.setCursor(customCursor);
    }

}
