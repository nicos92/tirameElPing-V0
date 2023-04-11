package com.example.tirameelping00;

import com.example.tirameelping00.hilos.EjecutarPingHilo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TirameElPingController {


    private Thread thread;

    @FXML
    private TextArea txtAreaSalida;

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
    private AnchorPane ventanaBienv;

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
    private RadioButton radBtn_Prueba;

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

    @FXML
    private TextArea txtIpInfo;




    public  void ejecutarPing() {
        try {
            String ip = txtIP.getText();

            String pingCmd = "ping" + selectRadioBtn() + ip;

            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            soloPing(p, ip);
            /*if (accion) {
                setPingTxt( ip, notify, p);
            }*/

        } catch (Exception n){
            System.out.println("ERROR ejecutar Ping: " + n.getMessage());
        }
    }

    private  void soloPing(Process p, String ip){
        Runnable run = new EjecutarPingHilo(p, ip);

        thread = new Thread(run);


        thread.start();





    }

    public void onPing(){
        ventanaPing.setVisible(true);
        btnPing.setStyle("-fx-background-color: #41b4d3; -fx-background-radius: 10" );
        btnIpInfo.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        ventanaBienv.setVisible(false);
        ventanaTxtSalida.setVisible(false);
        ventanaIpInfo.setVisible(false);
        txtIP.requestFocus();
        radioButton();
    }

    public void onTxtSalida(){
        ventanaTxtSalida.setVisible(true);
        btnPing.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        btnIpInfo.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );
        ventanaPing.setVisible(false);
        ventanaIpInfo.setVisible(false);

    }

    public void onIpInfo(){
        ventanaPing.setVisible(false);
        ventanaTxtSalida.setVisible(false);
        ventanaBienv.setVisible(false);
        ventanaIpInfo.setVisible(true);
        btnIpInfo.setStyle("-fx-background-color: #41b4d3; -fx-background-radius: 10");
        btnPing.setStyle("-fx-background-color: #bebebe; -fx-background-radius: 10" );


        ipConfigAll();


    }

    public void onBtnIniciar(){

        try{
            System.out.println( txtIP.getText() + " " + selectRadioBtn());

            btnDetener.setDisable(false);
            desactVentPing(true);
            btnIniciar.setDisable(true);

        }catch (Exception e){
            System.out.println("Error onBtnIniciar: " + e.getMessage());
        }
        ejecutarPing();

    }

    public void onBtnDetener(){
        thread.interrupt();
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);

        desactVentPing(false);

    }
    public void exitButton(){

        System.exit(0);
        Platform.exit();

    }

    public void radioButton(){
        txtCantPet.setDisable(radBtn_t.isSelected() || radBtn_Prueba.isSelected());

    }

    public String selectRadioBtn(){
        if (radBtn_t.isSelected()){
            return " -t ";
        }
        if (radBtn_n.isSelected()){
            return " -n " + cantPeticiones();
        }

        return " ";
    }

    public String cantPeticiones(){
        try{
            System.out.println("Cantidad de Peticiones: " + txtCantPet.getText());
            System.out.println(txtCantPet.getText().hashCode());
            if (txtCantPet.getText().hashCode() == 0){
                return  " ";
            };
            int cant = Integer.parseInt(txtCantPet.getText());
            return " " + cant + " ";
        }catch (NumberFormatException n){
            System.out.println("Number Error: " + n.getMessage());
        }

        return " ";
    }

    private void desactVentPing(boolean b) {

        labelIp.setDisable(b);
        txtIP.setDisable(b);
        radBtn_Prueba.setDisable(b);
        radBtn_t.setDisable(b);
        radBtn_n.setDisable(b);
        txtCantPet.setDisable(b);
        host_a.setDisable(b);
        pingEnTxt.setDisable(b);
    }

    public  void ipConfigAll() {
        try {
            String pingCmd = "ipconfig";
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);
            ejecutarIpConfig(p);
        } catch (Exception n){
            System.out.println("EERRORR: " + n.getMessage());
        }
    }

    private  void ejecutarIpConfig(Process p) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.ISO_8859_1));
        String inputLine;
        txtIpInfo.setText("");
        while ((inputLine = lector.readLine()) != null )
        {
            txtIpInfo.setText( txtIpInfo.getText() + " \n "+ inputLine);
        }
    }




}