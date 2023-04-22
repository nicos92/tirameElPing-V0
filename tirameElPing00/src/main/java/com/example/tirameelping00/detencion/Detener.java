package com.example.tirameelping00.detencion;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;

public class Detener {

    private Button btnIniciar;
    private Button btnDetener;
    private ProgressIndicator progressIndicator;
    private Text txtError;


    public Detener(){

    }

    public Detener(Button btnIniciar, Button btnDetener, ProgressIndicator progressIndicator, Text txtError) {
        this.btnIniciar = btnIniciar;
        this.btnDetener = btnDetener;
        this.progressIndicator = progressIndicator;
        this.txtError = txtError;

    }

    public void sendBtnDetener(){
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        progressIndicator.setVisible(false);
        txtError.setText("");


    }


}
