package com.example.tirameelping00;

import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.estilos.Style;
import com.example.tirameelping00.hilos.EjecutarPingHilo;
import com.example.tirameelping00.hilos.MiHilo;
import com.example.tirameelping00.sonido.Sonido;
import com.example.tirameelping00.ventana.DesactVentPing;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class TirameElPingController implements Initializable {


    private Runtime r;

    private final Sonido[] sonidos = new Sonido[10];

     static final Process[] processes = new Process[10];
     static final Thread[] threads = new Thread[10];

     @FXML
     private HBox ventBtnsTodo;

     @FXML
     private ImageView imgVol;

     @FXML
     private Slider volume;

    @FXML
    private Button btnMultiPing, btnRegPing, btnPing;

    @FXML
    private TextField nomIp1, nomIp2, nomIp3, nomIp4, nomIp5, nomIp6, nomIp7, nomIp8, nomIp9;

    @FXML
    private Button  btnIniciar, btnIniciar1, btnIniciar2, btnIniciar3, btnIniciar4,btnIniciar5, btnIniciar6,
                    btnIniciar7, btnIniciar8, btnIniciar9, btnIniciarTodo;



    @FXML
    private Button  btnDetener, btnDetener1, btnDetener2, btnDetener3, btnDetener4, btnDetener5, btnDetener6,
                    btnDetener7, btnDetener8, btnDetener9, btnDetenerTodo;

    @FXML
    private ProgressIndicator progress; //, progress1, progress2, progress3, progress4, progress5, progress6, progress7,
            //progress8, progress9;
    @FXML
    private TextField txtIP1, txtIP2, txtIP3, txtIP4, txtIP5, txtIP6, txtIP7, txtIP8, txtIP9;
    @FXML
    private RadioButton radBtn_t1, radBtn_t2, radBtn_t3, radBtn_t4,radBtn_t5, radBtn_t6, radBtn_t7, radBtn_t8,
            radBtn_t9;

    @FXML
    private AnchorPane mainStage, ventanaPing, ventanaTxtSalida;

    @FXML
    private VBox ventMenu;

    @FXML
    private ScrollPane scrollMultiPing;

    @FXML
    private Label labelIp;

    @FXML
    private TextField txtIP, txtIpv4, txtHostName, txtCanoHostName, txtIpPublic;

    @FXML
    private RadioButton radBtn_Prueba, radBtn_t, radBtn_n;

    @FXML
    private TextField txtCantPet;

    @FXML
    private CheckBox host_a, pingEnTxt;

    @FXML
    private TextArea  txtAreaSalida;

    @FXML
    private TextField txtRutaArchivo;

    @FXML
    private Text txtError, txtError1, txtError2, txtError3, txtError4, txtError5, txtError6, txtError7,
            txtError8, txtError9;






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
        btnPing.setStyle(Style.ventElegida());
        btnMultiPing.setStyle(Style.ventApagada() );
        btnRegPing.setStyle(Style.ventApagada() );


        ventanaTxtSalida.setVisible(false);

        ventBtnsTodo.setVisible(false);
        scrollMultiPing.setVisible(false);
        txtIP.toBack();
        radioButton();


    }
    public void onVentMultiPing(){
        ventanaPing.setVisible(false);
        btnMultiPing.setStyle(Style.ventElegida());
        btnPing.setStyle(Style.ventApagada() );
        btnRegPing.setStyle(Style.ventApagada() );

        ventanaTxtSalida.setVisible(false);
        ventBtnsTodo.setVisible(true);

        scrollMultiPing.setVisible(true);


    }

    public void onVentTxtSalida(){
        ventanaTxtSalida.setVisible(true);
        btnRegPing.setStyle(Style.ventElegida());
        btnPing.setStyle(Style.ventApagada() );
        btnMultiPing.setStyle(Style.ventApagada());
        ventanaPing.setVisible(false);
        ventBtnsTodo.setVisible(false);

        scrollMultiPing.setVisible(false);
    }

    public void cerrarVentMenu(){
        btnPing.setVisible(false);
        btnMultiPing.setVisible(false);
        btnRegPing.setVisible(false);
        //lblVolume.setVisible(false);
        volume.setVisible(false);
        imgVol.setVisible(false);
        ventMenu.prefWidthProperty().bind(mainStage.getScene().getWindow().widthProperty().multiply(0.05));
    }

    public void abrirVentMenu(){
        btnPing.setVisible(true);
        btnMultiPing.setVisible(true);
        btnRegPing.setVisible(true);
        //lblVolume.setVisible(true);
        imgVol.setVisible(true);
        volume.setVisible(true);

        ventMenu.prefWidthProperty().bind(mainStage.getScene().getWindow().widthProperty().multiply(0.15));
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

    public void setImgMute(){
        //Sonido.setGainControl(volume.getValue());
        if (volume.getValue() == 0){
            Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/mute.png")));
            imgVol.setImage(image1);
        }else {
            Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("imgs/vol.png")));
            imgVol.setImage(image2);

        }
    }

    public  void ejecutarPing() {
        try {

            if (threads[0] != null) threads[0].interrupt();

            sonidos[0] = new Sonido();
            String pingCmd = "ping " +  txtIP.getText() + selectRadioBtn();
            r = Runtime.getRuntime();
            processes[0] = r.exec(pingCmd);

            //Detener detener = new Detener(btnIniciar,btnDetener, progress, txtError);
            DesactVentPing desactPing = new DesactVentPing(labelIp,txtIP,radBtn_Prueba,radBtn_t,radBtn_n,txtCantPet, host_a,pingEnTxt);
            EjecutarPingHilo runClass = new EjecutarPingHilo(processes[0], txtIP.getText(), pingEnTxt.isSelected(), txtAreaSalida,
                    txtRutaArchivo, desactPing, btnIniciar, btnDetener, progress, txtError, volume, sonidos[0]);

            //340480_ATf movistar club
            threads[0] = new Thread(runClass);
            threads[0].start();

        } catch (Exception n){
            System.out.println("ERROR ejecutar Ping: " + n.getMessage());
        }
    }

    public  void ejecutarMultiPing(int id, TextField _txtIP, Button _btnIniciar, Button _btnDetener,
                                    RadioButton _radBtn, TextField _nomIp, Text _txtError) {
        try {

            if (threads[id] != null) threads[id].interrupt();
            // prepara el comando CMD
            String cmd = "ping " + _txtIP.getText() + (_radBtn.isSelected() ? " -t " : " ");
             r = Runtime.getRuntime();
             processes[id] = r.exec(cmd);

            sonidos[id] = new Sonido();

            // detiene el proceso
            /*Detener detener = new Detener(btnIniciar,btnDetener, progress, txtError);*/
            Detener detener = new Detener( _btnIniciar, _btnDetener);
            // desactiva los elementos
            DesactVentPing desactVentPing = new DesactVentPing(_txtIP, _radBtn, _nomIp);

            // ejecuta el hilo
            MiHilo miHilo = new MiHilo(processes[id], _txtIP, detener, desactVentPing, _nomIp, _txtError, volume, sonidos[id]);
            threads[id]= new Thread(miHilo);
            threads[id].start();
            desactFilaMultiPing(_nomIp, _txtIP,_radBtn, _btnIniciar, _btnDetener);

        } catch (Exception n){
            System.out.println("ERROR ejecutar Multi Ping: " + n.getMessage());
        }
    }

    public void desactFilaMultiPing(TextField _nomIp, TextField _txtIP, RadioButton _radBtn, Button _btnIniciar, Button _btnDetener){
        _nomIp.setDisable(true);
        _txtIP.setDisable(true);
        _radBtn.setDisable(true);
        _btnIniciar.setDisable(true);
        _btnDetener.setDisable(false);
        //_progress.setVisible(true);
    }

    public void iniciarTodoMultiPing(){
        Platform.runLater(() -> btnTodos(true));
        for( int i = 1; i < threads.length; i++){
            if (threads[i] == null || !threads[i].isAlive()){
                //InitMultiPing init = new InitMultiPing(threads, processes);
                altaHilos(i);
            }
        }
        Platform.runLater(() -> btnTodos(false));
    }

    public void btnTodos(boolean b){
        btnIniciarTodo.setDisable(b);
        btnDetenerTodo.setDisable(b);
    }


    public void altaHilos(int id){
        switch (id){
            case 1 -> ejecutarMultiPing(id, txtIP1, btnIniciar1, btnDetener1,  radBtn_t1, nomIp1, txtError1);
            case 2 -> ejecutarMultiPing(id, txtIP2, btnIniciar2, btnDetener2,  radBtn_t2, nomIp2, txtError2);
            case 3 -> ejecutarMultiPing(id, txtIP3, btnIniciar3, btnDetener3,  radBtn_t3, nomIp3, txtError3);
            case 4 -> ejecutarMultiPing(id, txtIP4, btnIniciar4, btnDetener4,  radBtn_t4, nomIp4, txtError4);
            case 5 -> ejecutarMultiPing(id, txtIP5, btnIniciar5, btnDetener5,  radBtn_t5, nomIp5, txtError5);
            case 6 -> ejecutarMultiPing(id, txtIP6, btnIniciar6, btnDetener6,  radBtn_t6, nomIp6, txtError6);
            case 7 -> ejecutarMultiPing(id, txtIP7, btnIniciar7, btnDetener7,  radBtn_t7, nomIp7, txtError7);
            case 8 -> ejecutarMultiPing(id, txtIP8, btnIniciar8, btnDetener8,  radBtn_t8, nomIp8, txtError8);
            case 9 -> ejecutarMultiPing(id, txtIP9, btnIniciar9, btnDetener9,  radBtn_t9, nomIp9, txtError9);
        }
    }

     public void exitButton(){
         closeThreadProcess();
         Platform.exit();
         System.exit(0);
    }



     public void closeThreadProcess() {
         Platform.runLater(() -> btnTodos(true));

         for( int i = 1; i < threads.length; i++){
             if (threads[i] !=  null && threads[i].isAlive())threads[i].interrupt();
         }
         for( int i = 1; i < processes.length; i++){
             if (processes[i] != null)processes[i].destroy();
         }

         Platform.runLater(() -> btnTodos(false));

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
            if (txtCantPet.getText().hashCode() == 0)return  " ";

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
            txtIpv4.setText( InetAddress.getLocalHost().getHostAddress());
            txtHostName.setText( InetAddress.getLocalHost().getHostName());
            txtCanoHostName.setText( InetAddress.getLocalHost().getCanonicalHostName());

        } catch (IOException  e) {
            throw new RuntimeException(e);
        }
    }


    public  void saberIpPublic() {


            if (txtIpPublic.getText().equals("")){

                try {
                txtError.setText("");
                URL url_name = new URI("http://myexternalip.com/raw").toURL();
                BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
                // reads system IPAddress
                txtIpPublic.setText( sc.readLine().trim());
                } catch (IOException | URISyntaxException e) {
                    txtError.setText("No se obtener IP Publica: " + e.getMessage());
                }

            }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ipConfigAll();
        //DesktopNotify.setDefaultTheme(NotifyTheme.Light);
        btnPing.setOnAction(a -> onVentPing());
        btnMultiPing.setOnAction(a -> onVentMultiPing());
        btnRegPing.setOnAction(a -> onVentTxtSalida());
        volume.addEventHandler(MouseEvent.MOUSE_DRAGGED, (e) ->{
            //sonido.setGainControl(volume.getValue());
            for (Sonido son : sonidos){
                if (son != null)son.setGainControl(volume.getValue());
            }
        } );
        Platform.setImplicitExit(false);


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
        if (event.contains("btnIniciar7")){
            altaHilos(7);
        }
        if (event.contains("btnIniciar8")){
            altaHilos(8);
        }
        if (event.contains("btnIniciar9")){
            altaHilos(9);
        }

    }

    public void onBtnDetenerMulti(javafx.scene.input.MouseEvent mouseEvent){

        String event = mouseEvent.getSource().toString();


        if (event.contains("btnDetener1")){
            btnIniciar1.setDisable(false);
            btnDetener1.setDisable(true);
            //progress1.setVisible(false);
            threads[1].interrupt();
            processes[1].destroy();
        }
        if (event.contains("btnDetener2")){
            btnIniciar2.setDisable(false);
            btnDetener2.setDisable(true);
            //progress2.setVisible(false);
            threads[2].interrupt();
            processes[2].destroy();
        }
        if (event.contains("btnDetener3")){
            btnIniciar3.setDisable(false);
            btnDetener3.setDisable(true);
            //progress3.setVisible(false);
            threads[3].interrupt();
            processes[3].destroy();
        }
        if (event.contains("btnDetener4")){
            btnIniciar4.setDisable(false);
            btnDetener4.setDisable(true);
            //progress4.setVisible(false);
            threads[4].interrupt();
            processes[4].destroy();

        }
        if (event.contains("btnDetener5")){
            btnIniciar5.setDisable(false);
            btnDetener5.setDisable(true);
            //progress5.setVisible(false);
            threads[5].interrupt();
            processes[5].destroy();
        }
        if (event.contains("btnDetener6")){
            btnIniciar6.setDisable(false);
            btnDetener6.setDisable(true);
            //progress6.setVisible(false);
            threads[6].interrupt();
            processes[6].destroy();
        }
        if (event.contains("btnDetener7")){
            btnIniciar7.setDisable(false);
            btnDetener7.setDisable(true);
            //progress7.setVisible(false);
            threads[7].interrupt();
            processes[7].destroy();
        }
        if (event.contains("btnDetener8")){
            btnIniciar8.setDisable(false);
            btnDetener8.setDisable(true);
            //progress8.setVisible(false);
            threads[8].interrupt();
            processes[8].destroy();
        }
        if (event.contains("btnDetener9")){
            btnIniciar9.setDisable(false);
            btnDetener9.setDisable(true);
            //progress9.setVisible(false);
            threads[9].interrupt();
            processes[9].destroy();
        }


    }
}