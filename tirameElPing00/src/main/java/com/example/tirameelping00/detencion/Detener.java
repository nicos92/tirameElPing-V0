package com.example.tirameelping00.detencion;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;

public class Detener {

    private final Button btnIniciar;
    private final Button btnDetener;
    private Button pos;
    private  ProgressIndicator progressIndicator;


    public Detener(Button btnIniciar, Button btnDetener, ProgressIndicator progressIndicator) {
        this.btnIniciar = btnIniciar;
        this.btnDetener = btnDetener;
        this.progressIndicator = progressIndicator;



    }
    public Detener(Button btnIniciar, Button btnDetener, Button pos) {
        this.btnIniciar = btnIniciar;
        this.btnDetener = btnDetener;
        this.pos = pos;

    }

    public void sendBtnDetener(){
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        progressIndicator.setVisible(false);


    }

    public void sendBtnDetenerMulti(){
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        pos.setDisable(false);

    }




}
