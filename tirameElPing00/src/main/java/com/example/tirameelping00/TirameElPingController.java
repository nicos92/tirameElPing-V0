package com.example.tirameelping00;

import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.hilos.EjecutarPingHilo;
import com.example.tirameelping00.ventana.DesactVentPing;
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
        txtIP.toBack();
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

        btnIniciar.setDisable(true);
        btnDetener.setDisable(false);
        progress.setVisible(true);
        txtError.setText("");

        desactVentPing(true);
        ejecutarPing();
        onTxtSalida();
    }

    public void onBtnDetener(){
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        progress.setVisible(false);
        txtError.setText("");

        thread.interrupt();
        desactVentPing(false);

    }

    public  void ejecutarPing() {
        try {

            String ip = txtIP.getText();
            String pingCmd = "ping" + selectRadioBtn() + ip;
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);
            System.out.println("ping en  txt: " + pingEnTxt.isSelected());
            Detener detener = new Detener(btnIniciar,btnDetener, progress, txtError);
            DesactVentPing desactPing = new DesactVentPing(labelIp,txtIP,radBtn_Prueba,radBtn_t,radBtn_n,txtCantPet, host_a,pingEnTxt);
            EjecutarPingHilo runClass = new EjecutarPingHilo(p, ip, pingEnTxt.isSelected(), txtAreaSalida,
                    txtRutaArchivo, detener, desactPing);
            thread = new Thread(runClass);
            thread.start();

        } catch (Exception n){
            System.out.println("ERROR ejecutar Ping: " + n.getMessage());
        }

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
            txtIpInfo.setText("");
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