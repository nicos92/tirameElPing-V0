package com.example.tirameelping00;

import com.example.tirameelping00.hilos.EjecutarPingHilo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TirameElPingController {


    private Thread thread;

    @FXML
    private ProgressIndicator progress;



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


    @FXML
    private TextArea txtAreaSalida;

    @FXML
    private TextField txtRutaArchivo;

    @FXML
    private Text txtError;



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
            btnDetener.setDisable(false);
            desactVentPing(true);
            btnIniciar.setDisable(true);
            txtError.setText("");

        }catch (Exception e){
            System.out.println("Error onBtnIniciar: " + e.getMessage());
        }
        ejecutarPing();
        progress.setVisible(true);
    }

    public  void ejecutarPing() {
        try {

            String ip = txtIP.getText();
            String pingCmd = "ping" + selectRadioBtn() + ip;
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);
            System.out.println("ping en  txt: " + pingEnTxt.isSelected());
            EjecutarPingHilo runClass = new EjecutarPingHilo(p, ip, pingEnTxt.isSelected(), txtAreaSalida, txtRutaArchivo);
            thread = new Thread(runClass);
            thread.start();

        } catch (Exception n){
            System.out.println("ERROR ejecutar Ping: " + n.getMessage());
        }

    }



    public void onBtnDetener(){
        thread.interrupt();
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        progress.setVisible(false);
        desactVentPing(false);
        txtError.setText("");
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
            }
            int cant = Integer.parseInt(txtCantPet.getText());
            return " " + cant + " ";
        }catch (NumberFormatException n){
            txtError.setText("No es un numero entero");
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
            List<String> miIpList  = new ArrayList<>();
            miIpList.add("Dirección IPv4: " + InetAddress.getLocalHost().getHostAddress());
            miIpList.add("Host Name: " + InetAddress.getLocalHost().getHostName());
            miIpList.add("Canonical Host Name: " + InetAddress.getLocalHost().getCanonicalHostName());

            try
            {
                URL url_name = new URL("http://myexternalip.com/raw");

                BufferedReader sc =
                        new BufferedReader(new InputStreamReader(url_name.openStream()));

                // reads system IPAddress
                miIpList.add("IP Publica: " + sc.readLine().trim());
            }
            catch (Exception e)
            {
                miIpList.add("IP Publica: " + "No se puede obtener");
            }
            for (String data :
                    miIpList) {
                txtIpInfo.setText( txtIpInfo.getText() + " \n "+ data);
            }


        } catch (Exception n){
            System.out.println("ERROR : " + n.getMessage());
        }


    }






}