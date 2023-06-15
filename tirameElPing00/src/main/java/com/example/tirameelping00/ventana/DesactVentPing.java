package com.example.tirameelping00.ventana;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class DesactVentPing {
    private Label labelIp;
    private final TextField txtIP;
    private  RadioButton radBtn_Prueba;
    private final RadioButton radBtn_t;
    private  RadioButton radBtn_n;
    private  TextField txtCantPet;
    private  CheckBox host_a;
    private  CheckBox pingEnTxt;

    private TextField nomIp;

    public DesactVentPing(Label labelIp, TextField txtIP, RadioButton radBtn_Prueba, RadioButton radBtn_t, RadioButton radBtn_n, TextField txtCantPet, CheckBox host_a, CheckBox pingEnTxt) {
        this.labelIp = labelIp;
        this.txtIP = txtIP;
        this.radBtn_Prueba = radBtn_Prueba;
        this.radBtn_t = radBtn_t;
        this.radBtn_n = radBtn_n;
        this.txtCantPet = txtCantPet;
        this.host_a = host_a;
        this.pingEnTxt = pingEnTxt;

    }


    public DesactVentPing(TextField txtIP, RadioButton radBtn, TextField nomIp) {
        this.txtIP = txtIP;
        this.radBtn_t = radBtn;
        this.nomIp = nomIp;
    }

    public void desactItemsPing(boolean b) {
        labelIp.setDisable(b);
        txtIP.setDisable(b);
        radBtn_Prueba.setDisable(b);
        radBtn_t.setDisable(b);
        radBtn_n.setDisable(b);
        txtCantPet.setDisable(b);
        host_a.setDisable(b);
        pingEnTxt.setDisable(b);
    }

    public void desactItemsPingMulti(boolean b) {

        radBtn_t.setDisable(b);
        txtIP.setDisable(b);
        nomIp.setDisable(b);
    }
}
