package com.example.tirameelping00;

import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.hilos.EjecutarPingHilo;
import com.example.tirameelping00.hilos.MiHilo;
import com.example.tirameelping00.ventana.DesactVentPing;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class TirameElPingController implements Initializable {


    private Thread thread;

    private final List<MiHilo> threads = new ArrayList<>();


    @FXML
    private ProgressIndicator progress;


    @FXML
    private Button btnRegPing;

    @FXML
    private Button btnPing;

    @FXML
    private Button btnIpInfo;

    @FXML
    private Button btnIniciar;

/*    @FXML
    private Button btnIniciar1;

    @FXML
    private Button btnIniciar2;

    @FXML
    private Button btnIniciar3;

    @FXML
    private Button btnIniciar4;

    @FXML
    private Button btnIniciar5;

    @FXML
    private Button btnIniciar6;*/

    @FXML
    private TextField txtIP1;
    @FXML
    private TextField txtIP2;
    @FXML
    private TextField txtIP3;
    @FXML
    private TextField txtIP4;
    @FXML
    private TextField txtIP5;
    @FXML
    private TextField txtIP6;
    @FXML
    private RadioButton radBtn_t1;
    @FXML
    private RadioButton radBtn_t2;
    @FXML
    private RadioButton radBtn_t3;
    @FXML
    private RadioButton radBtn_t4;
    @FXML
    private RadioButton radBtn_t5;
    @FXML
    private RadioButton radBtn_t6;

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

    @FXML
    private AnchorPane base1;

    @FXML
    private VBox ventanaMultiPing;

    @FXML
    private Button btnMultiPing;

    @FXML
    private ImageView close;

    @FXML
    private ImageView minus;

    @FXML
    private void onMinimize(){
        ((Stage) base1.getScene().getWindow()).setIconified(true);
    }

    public void enteredImageMinus(){
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/icons8-minus-64.png")));
        minus.setImage(image1);
    }

    public void exitedImageMinus(){
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/icons8-minus-sign-96.png")));
        minus.setImage(image1);
    }

    public void enteredImageClose(){
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/icons8-close-48.png")));
        close.setImage(image1);
    }

    public void exitedImageClose(){
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/icons8-close-48 (2).png")));
        close.setImage(image1);
    }

    public void onPing(){
        ventanaPing.setVisible(true);
        btnPing.setStyle("-fx-background-color: #ffffff; -fx-border-color: black; -fx-border-width: 0px 2px 0px 2px;" );
        btnIpInfo.setStyle("-fx-background-color: #D0D0D0; -fx-border-color: black; -fx-border-width: 0px 2px 0px 2px;" );
        btnMultiPing.setStyle("-fx-background-color: #D0D0D0;  -fx-border-color: black; -fx-border-width: 0px 2px 0px" +
                " 0px;" );
        btnRegPing.setStyle("-fx-background-color: #D0D0D0; ;" );
        ventanaBienv.setVisible(false);
        ventanaTxtSalida.setVisible(false);
        ventanaIpInfo.setVisible(false);
        ventanaMultiPing.setVisible(false);
        txtIP.requestFocus();
        txtIP.toBack();
        radioButton();
    }
    public void onMultiPing(){
        ventanaPing.setVisible(false);
        btnMultiPing.setStyle("-fx-background-color: #ffffff;  -fx-border-color: black; -fx-border-width: 0px 2px 0px" +
                " 0px;" );
        btnIpInfo.setStyle("-fx-background-color: #D0D0D0;  -fx-border-color: black; -fx-border-width: 0px 2px 0px 2px;" );
        btnPing.setStyle("-fx-background-color: #D0D0D0;  -fx-border-color: black; -fx-border-width: 0px 2px 0px 2px;" );
        btnRegPing.setStyle("-fx-background-color: #D0D0D0; " );
        ventanaBienv.setVisible(false);
        ventanaTxtSalida.setVisible(false);
        ventanaIpInfo.setVisible(false);
        ventanaMultiPing.setVisible(true);


    }

    public void onTxtSalida(){
        ventanaTxtSalida.setVisible(true);
        btnRegPing.setStyle("-fx-background-color: #ffffff; " );
        btnPing.setStyle("-fx-background-color: #D0D0D0;  -fx-border-color: black; -fx-border-width: 0px 2px 0px 2px;" );
        btnIpInfo.setStyle("-fx-background-color: #D0D0D0;  -fx-border-color: black; -fx-border-width: 0px 2px 0px 2px;");
        btnMultiPing.setStyle("-fx-background-color: #D0D0D0;  -fx-border-color: black; -fx-border-width: 0px 2px 0px" +
                " 0px;" );
        ventanaPing.setVisible(false);
        ventanaIpInfo.setVisible(false);
        ventanaMultiPing.setVisible(false);
    }

    public void onIpInfo(){
        ventanaPing.setVisible(false);
        ventanaTxtSalida.setVisible(false);
        ventanaBienv.setVisible(false);
        ventanaMultiPing.setVisible(false);
        ventanaIpInfo.setVisible(true);
        btnIpInfo.setStyle("-fx-background-color: #ffffff;  -fx-border-color: black; -fx-border-width: 0px 2px 0px 2px;");
        btnPing.setStyle("-fx-background-color: #D0D0D0;  -fx-border-color: black; -fx-border-width: 0px 2px 0px 2px;" );
        btnMultiPing.setStyle("-fx-background-color: #D0D0D0;  -fx-border-color: black; -fx-border-width: 0px 2px 0px" +
                " 0px;" );
        btnRegPing.setStyle("-fx-background-color: #D0D0D0; " );
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

            Detener detener = new Detener(btnIniciar,btnDetener, progress, txtError);
            DesactVentPing desactPing = new DesactVentPing(labelIp,txtIP,radBtn_Prueba,radBtn_t,radBtn_n,txtCantPet, host_a,pingEnTxt);
            EjecutarPingHilo runClass = new EjecutarPingHilo(p, ip, pingEnTxt.isSelected(), txtAreaSalida,
                    txtRutaArchivo, detener, desactPing, thread);
            thread = new Thread(runClass);
            thread.start();


        } catch (Exception n){
            System.out.println("ERROR ejecutar Ping: " + n.getMessage());
        }

    }

    public  void ejecutarMultiPing(String IP) {
        try {


            String pingCmd = "ping" + IP;
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            // detiene el proceso
            Detener detener = new Detener(btnIniciar,btnDetener, progress, txtError);

            // desactiva los elementos
            DesactVentPing desactPing = new DesactVentPing(labelIp,txtIP,radBtn_Prueba,radBtn_t,radBtn_n,txtCantPet, host_a,pingEnTxt);

            // ejecuta el hilo
            EjecutarPingHilo runClass = new EjecutarPingHilo(p, IP, pingEnTxt.isSelected(), txtAreaSalida,
                    txtRutaArchivo, detener, desactPing, thread);
            thread = new Thread(runClass);
            thread.start();


        } catch (Exception n){
            System.out.println("ERROR ejecutar Ping: " + n.getMessage());
        }

    }

    public void altaThreads(int id){
        boolean flag = false;
        for (MiHilo hilo : threads) {
            if (hilo.getId() != id){
                    flag = true;
            }
        }

        if (flag){
            MiHilo miHilo = new MiHilo(id);
            threads.add(miHilo);
        }

        switch (id){
            case 1 -> {
                String space = radBtn_t1.isSelected() ? " -t " : " ";
                ejecutarMultiPing(space + txtIP1.getText());
            }
            case 2 -> {
                String space = radBtn_t2.isSelected() ? " -t " : " ";
                ejecutarMultiPing(space + txtIP2.getText());
            }
            case 3 -> {
                String space = radBtn_t3.isSelected() ? " -t " : " ";
                ejecutarMultiPing(space + txtIP3.getText());
            }
            case 4 -> {
                String space = radBtn_t4.isSelected() ? " -t " : " ";
                ejecutarMultiPing(space + txtIP4.getText());
            }
            case 5 -> {
                String space = radBtn_t5.isSelected() ? " -t " : " ";
                ejecutarMultiPing(space + txtIP5.getText());
            }
            case 6 -> {
                String space = radBtn_t6.isSelected() ? " -t " : " ";
                ejecutarMultiPing(space + txtIP6.getText());
            }
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
            for (String data : miIpList) {
                txtIpInfo.setText( txtIpInfo.getText() + " \n "+ data);
            }
        } catch (Exception n){
            System.out.println("ERROR : " + n.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void btnIniciarMultiPing(javafx.scene.input.MouseEvent mouseEvent) {

        System.out.println(mouseEvent.getSource());

        String event = mouseEvent.getSource().toString();

        if (event.contains("btnIniciar1")){
            altaThreads(1);
        }
        if (event.contains("btnIniciar2")){
            altaThreads(2);
        }
        if (event.contains("btnIniciar3")){
            altaThreads(3);
        }
        if (event.contains("btnIniciar4")){
            altaThreads(4);
        }
        if (event.contains("btnIniciar5")){
            altaThreads(5);
        }
        if (event.contains("btnIniciar6")){
            altaThreads(6);
        }



    }
}