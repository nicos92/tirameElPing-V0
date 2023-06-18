package com.example.tirameelping00;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

import static com.example.tirameelping00.TirameElPingController.closeThreadProcess;
import static com.example.tirameelping00.TirameElPingController.exitButton;

public class TirameElPingApp extends Application {



    double yOffset;
    double xOffset;
    @Override
    public void start(Stage stage) throws IOException {

        //FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("design.fxml"));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("desingTEP.fxml")));

        FXTrayIcon trayIcon = new FXTrayIcon(stage, Objects.requireNonNull(getClass().getResource("imgs/rj45.png")));
        trayIcon.show();


        // By default the FXTrayIcon's tooltip will be the parent stage's title, that we used in the constructor
        // This method can override this
        trayIcon.setTrayIconTooltip("Tirame el Ping!");
        
        // We can also nest menus, below is an Options menu with sub-items
        //MenuItem menuOptions = new MenuItem("Options");

        MenuItem detenerTodo = new MenuItem("Detener Todo");
        detenerTodo.setOnAction(e -> closeThreadProcess());

        MenuItem miOff = new MenuItem("Cerrar");
        miOff.setOnAction(e -> exitButton());



        //trayIcon.addMenuItem(miOff);
        trayIcon.addMenuItems(detenerTodo, miOff);


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

        try{
            Scene scene = new Scene(root);
            stage.setTitle("Tirame El Ping");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/rj45.png"))));
            stage.initStyle(StageStyle.DECORATED);
            stage.setResizable(false);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toString());
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println("ERROR start: " + e.getMessage());
        }

    }


    public static void main(String[] args) {
        launch();
    }
}