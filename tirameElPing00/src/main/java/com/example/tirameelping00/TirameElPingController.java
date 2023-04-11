package com.example.tirameelping00;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private Button btnIniciar;

    @FXML
    private Button btnDetener;

    @FXML
    private AnchorPane ventanaPing;

    @FXML
    private AnchorPane ventanaTxtSalida;

    @FXML
    private AnchorPane ventanaIpInfo;

    @FXML
    private Label labelIp;

    @FXML
    private TextField txtIP;

    @FXML
    private RadioButton radBtn_t;

    @FXML
    private RadioButton radBtn_n;

    @FXML
    private TextField txtCantPet;

    @FXML
    private CheckBox host_a;

    @FXML
    private CheckBox pingEnTxt;



    public void onPing(){
        ventanaPing.setVisible(true);
        btnPing.setStyle("" );
        btnTxtSalida.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        btnIpInfo.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        ventanaTxtSalida.setVisible(false);
        ventanaIpInfo.setVisible(false);
        txtIP.requestFocus();
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

    public void onBtnIniciar(){
        btnIniciar.setDisable(true);
        btnDetener.setDisable(false);
        desactVentPing(true);

    }

    public void onBtnDetener(){
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);

        desactVentPing(false);

    }
    public void exitButton(){

        System.exit(0);
        Platform.exit();

    }

    public void radioButton(){
        txtCantPet.setDisable(radBtn_t.isSelected());

    }

    private void desactVentPing(boolean b) {

        labelIp.setDisable(b);
        txtIP.setDisable(b);
        radBtn_t.setDisable(b);
        radBtn_n.setDisable(b);
        txtCantPet.setDisable(b);
        host_a.setDisable(b);
        pingEnTxt.setDisable(b);
    }




}