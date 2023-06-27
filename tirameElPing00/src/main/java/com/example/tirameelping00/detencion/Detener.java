package com.example.tirameelping00.detencion;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;

public class Detener {

    private final Button btnIniciar;
    private final Button btnDetener;
    private  ProgressIndicator progressIndicator;
    private Text txtError;

    public Detener(Button btnIniciar, Button btnDetener, ProgressIndicator progressIndicator, Text txtError) {
        this.btnIniciar = btnIniciar;
        this.btnDetener = btnDetener;
        this.progressIndicator = progressIndicator;
        this.txtError = txtError;


    }
    public Detener(Button btnIniciar, Button btnDetener) {
        this.btnIniciar = btnIniciar;
        this.btnDetener = btnDetener;
        //this.progressIndicator = progressIndicator;

    }

    public void sendBtnDetener(){
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        progressIndicator.setVisible(false);
        txtError.setText("");

    }

    public void sendBtnDetenerMulti(){
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        //progressIndicator.setVisible(false);

    }




}
