package com.example.tirameelping00;

import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.detencion.Exit;
import com.example.tirameelping00.hilos.EjecutarPingHilo;
import com.example.tirameelping00.hilos.MiHilo;
import com.example.tirameelping00.ventana.DesactVentPing;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;



public class TirameElPingController implements Initializable {

    private Runtime r;

     static final Process[] processes = new Process[7];
     static final Thread[] threads = new Thread[7];

    private static final Exit exit = new Exit( threads, processes);


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

    @FXML
    private TextField nomIp1;

    @FXML
    private TextField nomIp2;

    @FXML
    private TextField nomIp3;

    @FXML
    private TextField nomIp4;

    @FXML
    private TextField nomIp5;

    @FXML
    private TextField nomIp6;

    @FXML
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
    private Button btnIniciar6;

    @FXML
    private Button btnDetener1;
    @FXML
    private Button btnDetener2;
    @FXML
    private Button btnDetener3;
    @FXML
    private Button btnDetener4;
    @FXML
    private Button btnDetener5;
    @FXML
    private Button btnDetener6;

    @FXML
    private ProgressIndicator progress1;
    @FXML
    private ProgressIndicator progress2;
    @FXML
    private ProgressIndicator progress3;
    @FXML
    private ProgressIndicator progress4;
    @FXML
    private ProgressIndicator progress5;
    @FXML
    private ProgressIndicator progress6;
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
    private VBox ventanaMultiPing;

    @FXML
    private Button btnMultiPing;


    /*    @FXML
    private void onMinimize(){

        ((Stage) base1.getScene().getWindow()).setIconified(true);
    }*/

    /*    public void enteredImageMinus(){
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/icons8-minus-64.png")));
        minus.setImage(image1);
    }

    public void exitedImageMinus(){
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/icons8-minus-sign-96.png")));
        minus.setImage(image1);
    }

    public void enteredImageClose(){
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/close1.png")));
        close.setImage(image1);
    }

    public void exitedImageClose(){
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/close2.png")));
        close.setImage(image1);
    }*/

    public void onVentPing(){
        ventanaPing.setVisible(true);
        btnPing.setStyle("-fx-background-color: #ffffff;  -fx-border-color: #00af13; -fx-border-width: 0px 0px 3px 0px;" );
        btnIpInfo.setStyle(" -fx-border-color: transparent; " );
        btnMultiPing.setStyle("  -fx-border-color: transparent;" );
        btnRegPing.setStyle("   -fx-border-color: transparent;" );
        ventanaBienv.setVisible(false);
        ventanaTxtSalida.setVisible(false);
        ventanaIpInfo.setVisible(false);
        ventanaMultiPing.setVisible(false);
        txtIP.toBack();
        radioButton();


    }
    public void onVentMultiPing(){
        ventanaPing.setVisible(false);
        btnMultiPing.setStyle("-fx-background-color: #ffffff;   -fx-border-color: #00af13; -fx-border-width: 0px 0px 3px 0px;" );
        btnIpInfo.setStyle("   -fx-border-color: transparent;" );
        btnPing.setStyle("   -fx-border-color: transparent;" );
        btnRegPing.setStyle("   -fx-border-color: transparent;" );
        ventanaBienv.setVisible(false);
        ventanaTxtSalida.setVisible(false);
        ventanaIpInfo.setVisible(false);
        ventanaMultiPing.setVisible(true);


    }

    public void onVentTxtSalida(){
        ventanaTxtSalida.setVisible(true);
        btnRegPing.setStyle("-fx-background-color: #ffffff;   -fx-border-color: #00af13; -fx-border-width: 0px 0px 3px 0px;" );
        btnPing.setStyle("  -fx-border-color: transparent;" );
        btnIpInfo.setStyle("   -fx-border-color: transparent;");
        btnMultiPing.setStyle("  -fx-border-color: transparent;" );
        ventanaPing.setVisible(false);
        ventanaIpInfo.setVisible(false);
        ventanaMultiPing.setVisible(false);
        ventanaBienv.setVisible(false);
    }

    public void onVentIpInfo(){
        ventanaPing.setVisible(false);
        ventanaTxtSalida.setVisible(false);
        ventanaBienv.setVisible(false);
        ventanaMultiPing.setVisible(false);
        ventanaIpInfo.setVisible(true);
        btnIpInfo.setStyle("-fx-background-color: #ffffff; -fx-border-color: #00af13; -fx-border-width: 0px 0px 3px 0px;");
        btnPing.setStyle("  -fx-border-color: transparent;" );
        btnMultiPing.setStyle(" -fx-border-color: transparent;" );
        btnRegPing.setStyle(" -fx-border-color: transparent;" );
        ipConfigAll();
    }

    public void onBtnIniciar(){

        btnIniciar.setDisable(true);
        btnDetener.setDisable(false);
        progress.setVisible(true);
        txtError.setText("");

        desactVentPing(true);
        ejecutarPing();
        //onVentTxtSalida();
    }

    public void onBtnDetener() {

        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        progress.setVisible(false);
        txtError.setText("");
        processes[0].destroy();
        threads[0].interrupt();
        desactVentPing(false);

    }

    public  void ejecutarPing() {
        try {

            if (threads[0] != null) threads[0].interrupt();


            String pingCmd = "ping " +  txtIP.getText() + selectRadioBtn();
            r = Runtime.getRuntime();
            processes[0] = r.exec(pingCmd);

            //Detener detener = new Detener(btnIniciar,btnDetener, progress, txtError);
            DesactVentPing desactPing = new DesactVentPing(labelIp,txtIP,radBtn_Prueba,radBtn_t,radBtn_n,txtCantPet, host_a,pingEnTxt);
            EjecutarPingHilo runClass = new EjecutarPingHilo(processes[0], txtIP.getText(), pingEnTxt.isSelected(), txtAreaSalida,
                    txtRutaArchivo, desactPing, btnIniciar, btnDetener, progress, txtError);

            //340480_ATf movistar club
            threads[0] = new Thread(runClass);
            threads[0].start();

        } catch (Exception n){
            System.out.println("ERROR ejecutar Ping: " + n.getMessage());
        }
    }

    public  void ejecutarMultiPing(int id, TextField _txtIP, Button _btnIniciar, Button _btnDetener,
                                   ProgressIndicator _progress, RadioButton _radBtn, TextField _nomIp) {
        try {

            desactFilaMultiPing(_nomIp, _txtIP,_radBtn, _btnIniciar, _btnDetener, _progress);

            if (threads[id] != null) threads[id].interrupt();
            // prepara el comando CMD
            String cmd = "ping " + _txtIP.getText() + (_radBtn.isSelected() ? " -t " : " ");
             r = Runtime.getRuntime();
             processes[id] = r.exec(cmd);

            // detiene el proceso
            /*Detener detener = new Detener(btnIniciar,btnDetener, progress, txtError);*/
            Detener detener = new Detener( _btnIniciar, _btnDetener, _progress);
            // desactiva los elementos
            /*DesactVentPing desactPing = new DesactVentPing(labelIp,txtIP,radBtn_Prueba,radBtn_t,radBtn_n,txtCantPet, host_a,pingEnTxt);*/
            DesactVentPing desactVentPing = new DesactVentPing(_txtIP, _radBtn, _nomIp);

            // ejecuta el hilo
            /*EjecutarPingHilo runClass = new EjecutarPingHilo(p, ip, pingEnTxt.isSelected(), txtAreaSalida,
                    txtRutaArchivo, detener, desactPing);*/
            MiHilo miHilo = new MiHilo(processes[id], _txtIP.getText(), detener, desactVentPing, _nomIp);
            threads[id]= new Thread(miHilo);
            threads[id].start();

        } catch (Exception n){
            System.out.println("ERROR ejecutar Multi Ping: " + n.getMessage());
        }
    }

    public void iniciarTodoMultiPing() {
        try {
            for( int i = 1; i < threads.length; i++){

                if (threads[i] == null || !threads[i].isAlive()){
                    altaHilos(i);
                    Thread.sleep(400);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void altaHilos(int id){
        switch (id){
            case 1 -> ejecutarMultiPing(id, txtIP1, btnIniciar1, btnDetener1, progress1, radBtn_t1, nomIp1);
            case 2 -> ejecutarMultiPing(id, txtIP2, btnIniciar2, btnDetener2, progress2, radBtn_t2, nomIp2);
            case 3 -> ejecutarMultiPing(id, txtIP3, btnIniciar3, btnDetener3, progress3, radBtn_t3, nomIp3);
            case 4 -> ejecutarMultiPing(id, txtIP4, btnIniciar4, btnDetener4, progress4, radBtn_t4, nomIp4);
            case 5 -> ejecutarMultiPing(id, txtIP5, btnIniciar5, btnDetener5, progress5, radBtn_t5, nomIp5);
            case 6 -> ejecutarMultiPing(id, txtIP6, btnIniciar6, btnDetener6, progress6, radBtn_t6, nomIp6);
        }
    }

     public void exitButton(){
        exit.exit();
    }

     public void closeThreadProcess() {
        exit.closeThreadsProcesses();
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
            if (txtIpInfo.getText().equals("")){

                String[] miIpInfo = new String[4];

                miIpInfo[0] = ("Dirección IPv4: " + InetAddress.getLocalHost().getHostAddress());

                miIpInfo[1] = ("Host Name: " + InetAddress.getLocalHost().getHostName());
                miIpInfo[2] = ("Canonical Host Name: " + InetAddress.getLocalHost().getCanonicalHostName());

                URL url_name = new URI("http://myexternalip.com/raw").toURL();
                BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
                // reads system IPAddress
                miIpInfo[3] = ("IP Publica: " + sc.readLine().trim());

                for (String data : miIpInfo) {
                txtIpInfo.appendText("\n" + data);
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
        btnPing.setOnAction(a -> onVentPing());
        btnMultiPing.setOnAction(a -> onVentMultiPing());
        btnIpInfo.setOnAction(a -> onVentIpInfo());
        btnRegPing.setOnAction(a -> onVentTxtSalida());
    }

    public void desactFilaMultiPing(TextField _nomIp, TextField _txtIP, RadioButton _radBtn, Button _btnIniciar, Button _btnDetener, ProgressIndicator _progress){
        _nomIp.setDisable(true);
        _txtIP.setDisable(true);
        _radBtn.setDisable(true);
        _btnIniciar.setDisable(true);
        _btnDetener.setDisable(false);
        _progress.setVisible(true);
    }


    public void btnIniciarMultiPing(MouseEvent mouseEvent) {

        String event = mouseEvent.getSource().toString();

        if (event.contains("btnIniciar1")){
            altaHilos(1);
        }
        if (event.contains("btnIniciar2")){
            altaHilos(2);
        }
        if (event.contains("btnIniciar3")){
            altaHilos(3);
        }
        if (event.contains("btnIniciar4")){
            altaHilos(4);
        }
        if (event.contains("btnIniciar5")){
            altaHilos(5);
        }
        if (event.contains("btnIniciar6")){
            altaHilos(6);
        }

    }

    public void onBtnDetenerMulti(javafx.scene.input.MouseEvent mouseEvent){

        String event = mouseEvent.getSource().toString();


        if (event.contains("btnDetener1")){
            btnIniciar1.setDisable(false);
            btnDetener1.setDisable(true);
            progress1.setVisible(false);
            threads[1].interrupt();
            processes[1].destroy();
        }
        if (event.contains("btnDetener2")){
            btnIniciar2.setDisable(false);
            btnDetener2.setDisable(true);
            progress2.setVisible(false);
            threads[2].interrupt();
            processes[2].destroy();
        }
        if (event.contains("btnDetener3")){
            btnIniciar3.setDisable(false);
            btnDetener3.setDisable(true);
            progress3.setVisible(false);
            threads[3].interrupt();
            processes[3].destroy();
        }
        if (event.contains("btnDetener4")){
            btnIniciar4.setDisable(false);
            btnDetener4.setDisable(true);
            progress4.setVisible(false);
            threads[4].interrupt();
            processes[4].destroy();

        }
        if (event.contains("btnDetener5")){
            btnIniciar5.setDisable(false);
            btnDetener5.setDisable(true);
            progress5.setVisible(false);
            threads[5].interrupt();
            processes[5].destroy();
        }
        if (event.contains("btnDetener6")){
            btnIniciar6.setDisable(false);
            btnDetener6.setDisable(true);
            progress6.setVisible(false);
            threads[6].interrupt();
            processes[6].destroy();
        }


    }
}