package com.example.tirameelping00;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class TirameElPingApp extends Application {



    double yOffset;
    double xOffset;
    @Override
    public void start(Stage stage) throws IOException {

        //FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("design.fxml"));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("desingTEP.fxml")));
        /*
         Methods top move application
         */

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });



        Scene scene = new Scene(root);
        stage.setTitle("Tirame El Ping");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/rj45.png"))));
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toString());
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}