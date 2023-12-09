package com.example.stickhero_2;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.net.URL;


public class HelloApplication extends Application {

    private static boolean flag = false;
    private static Scene scene;
    private static MediaPlayer mediaPlayer;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        HelloApplication.mediaPlayer = mediaPlayer;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playpage.fxml"));
        scene = new Scene(root);

        Image icon = new Image("hero.png");
        stage.getIcons().add(icon);

        stage.setTitle("StickHero");
        stage.setScene(scene);
        animations(scene);
        changeCursor();

        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            try {
                e.consume();
                logout(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });




        Media media = new Media(new File("src\\main\\resources\\bgmusic.mp3").toURI().toString());


        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        stage.show();
    }

    public void logout(Stage stage) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText("You're about to Close!");
        alert.setContentText("Are you sure you want to close?");

        if (alert.showAndWait().get() == ButtonType.OK){
            FileOutputStream fos1 = new FileOutputStream(new File("saveScore.txt"));
            ObjectOutputStream oos1 = new ObjectOutputStream(fos1);

            SavedGame saveScore = new SavedGame();

            oos1.writeObject(saveScore);
            oos1.close();fos1.close();
            System.out.println("You successfully logged out");
            stage.close();
        }
    }
    public static void animations(Scene scene){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), (Text) scene.lookup("#stickHeroText"));
        scaleTransition.setFromX(0.75);
        scaleTransition.setToX(1.25);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition.play();

        ImageView hero = (ImageView) scene.lookup("#playPageHero");
        Timeline imageSwitchTimeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            if (flag){
                hero.setImage(new Image("hero.png"));
                flag = false;
            }else {
                hero.setImage(new Image("hero1.png"));
                flag = true;
            }
        }));
        imageSwitchTimeline.setCycleCount(Timeline.INDEFINITE);
        imageSwitchTimeline.play();
    }

    public void changeCursor(){
        Image customCursorImage = new Image("cursor1.png");

        Cursor customCursor = new ImageCursor(customCursorImage);
        scene.setCursor(customCursor);

    }

    public static void main(String[] args) {
        Result result= JUnitCore.runClasses(TestClass.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
//        System.out.println(".....");
        System.out.println(result.wasSuccessful());
        launch();
    }
}