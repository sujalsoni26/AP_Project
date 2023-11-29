package com.example.stickhero_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("playpage.fxml"));
        Scene scene = new Scene(root);
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Images\\hero.png");
//        assert inputStream != null;
//        InputStream is = HelloApplication.class.getClassLoader().getResourceAsStream("Images/hero.png");

//        Image icon = new ImageIcon(ImageIO.read(is));
        Image icon = new Image("hero.png");
//        Image icon = new Image("src/main/resources/Images/pause.png");

        stage.getIcons().add(icon);

        stage.setTitle("StickHero");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}