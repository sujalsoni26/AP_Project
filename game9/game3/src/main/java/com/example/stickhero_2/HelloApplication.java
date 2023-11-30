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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;



public class HelloApplication extends Application {

    private static boolean flag = false;
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playpage.fxml"));
        scene = new Scene(root);

        Image icon = new Image("hero.png");
        stage.getIcons().add(icon);

        stage.setTitle("StickHero");
        stage.setScene(scene);

//        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), (Text) scene.lookup("#stickHeroText"));
//        scaleTransition.setFromX(0.75);
//        scaleTransition.setToX(1.25);
//        scaleTransition.setAutoReverse(true);
//        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
//        scaleTransition.play();
//
//        ImageView hero = (ImageView) scene.lookup("#playPageHero");
//        Timeline imageSwitchTimeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
//            if (flag){
//                hero.setImage(new Image("hero.png"));
//                flag = false;
//            }else {
//                hero.setImage(new Image("hero1.png"));
//                flag = true;
//            }
//        }));
//        imageSwitchTimeline.setCycleCount(Timeline.INDEFINITE);
//        imageSwitchTimeline.play();
        animations(scene);
        changeCursor();

        stage.setResizable(false);

        stage.show();
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
        launch();
    }
}