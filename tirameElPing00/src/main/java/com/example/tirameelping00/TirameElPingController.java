package com.example.tirameelping00;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;


public class TirameElPingController {

    @FXML
    private Button btnPing;
    @FXML
    private Button btnTxtSalida;

    @FXML
    private Button btnIpInfo;

    @FXML
    private AnchorPane ventanaPing;

    @FXML
    private AnchorPane ventanaTxtSalida;

    @FXML
    private AnchorPane ventanaIpInfo;

    @FXML
    private RadioButton radBtn_t;

    @FXML
    private TextField txtCantPet;


    public void onPing(){
        ventanaPing.setVisible(true);
        btnPing.setStyle("" );
        btnTxtSalida.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        btnIpInfo.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        ventanaTxtSalida.setVisible(false);
        ventanaIpInfo.setVisible(false);
    }

    public void onTxtSalida(){
        ventanaTxtSalida.setVisible(true);
        btnTxtSalida.setStyle("");
        btnPing.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        btnIpInfo.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        ventanaPing.setVisible(false);
        ventanaIpInfo.setVisible(false);

    }

    public void onIpInfo(){
        ventanaIpInfo.setVisible(true);
        btnIpInfo.setStyle("");
        btnPing.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        btnTxtSalida.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );

        ventanaPing.setVisible(false);
        ventanaTxtSalida.setVisible(false);


    }
    public void exitButton(){

        System.exit(0);
        Platform.exit();

    }

    public void radioButton(){
        if(radBtn_t.isSelected()){
            txtCantPet.setDisable(true);

        }
    }




}