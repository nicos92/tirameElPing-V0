package com.example.tirameelping00;

import com.example.tirameelping00.baseDatos.Basesita;
import com.example.tirameelping00.baseDatos.NomNumIp;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TirameElPingController implements Initializable {


    private  final String IPV4_PATTERN =
            "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";

    private  final Pattern pattern = Pattern.compile(IPV4_PATTERN);

    public  boolean isValidIp(final String dirIp) {
        Matcher matcher = pattern.matcher(dirIp);
        return matcher.matches();
    }
    private Runtime r;

    private final Sonido[] sonidos = new Sonido[11];

     static final Process[] processes = new Process[11];
     static final Thread[] threads = new Thread[11];

     @FXML
     private HBox ventBtnsTodo;

     @FXML
     private ImageView imgVol;

     @FXML
     private Slider volume;

    @FXML
    private Button btnMultiPing, btnRegPing, btnPing;

    @FXML
    private TextField nomIp1, nomIp2, nomIp3, nomIp4, nomIp5, nomIp6, nomIp7, nomIp8, nomIp9, nomIp10;

    @FXML
    private Button pos1, pos2, pos3, pos4, pos5, pos6, pos7, pos8, pos9, pos10;

    @FXML
    private Button  btnIniciar, btnIniciar1, btnIniciar2, btnIniciar3, btnIniciar4,btnIniciar5, btnIniciar6,
                    btnIniciar7, btnIniciar8, btnIniciar9, btnIniciar10, btnIniciarTodo;



    @FXML
    private Button  btnDetener, btnDetener1, btnDetener2, btnDetener3, btnDetener4, btnDetener5, btnDetener6,
                    btnDetener7, btnDetener8, btnDetener9, btnDetener10, btnDetenerTodo;

    @FXML
    private Button cont1, cont2, cont3, cont4, cont5, cont6, cont7, cont8, cont9, cont10;

    @FXML
    private ProgressIndicator progress; //, progress1, progress2, progress3, progress4, progress5, progress6, progress7,
            //progress8, progress9;
    @FXML
    private TextField txtIP1, txtIP2, txtIP3, txtIP4, txtIP5, txtIP6, txtIP7, txtIP8, txtIP9, txtIP10;
    @FXML
    private RadioButton radBtn_t1, radBtn_t2, radBtn_t3, radBtn_t4,radBtn_t5, radBtn_t6, radBtn_t7, radBtn_t8,
            radBtn_t9, radBtn_t10;

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
    private Text  txtError1, txtError2, txtError3, txtError4, txtError5, txtError6, txtError7,
            txtError8, txtError9, txtError10;






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


        desactVentPing(true);
        ejecutarPing();
        //onVentTxtSalida();
    }

    public void onBtnDetener() {

        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
        progress.setVisible(false);

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

            DesactVentPing desactPing = new DesactVentPing(labelIp,txtIP,radBtn_Prueba,radBtn_t,radBtn_n,txtCantPet, host_a,pingEnTxt);
            EjecutarPingHilo runClass = new EjecutarPingHilo(processes[0], txtIP.getText(), pingEnTxt.isSelected(), txtAreaSalida,
                    txtRutaArchivo, desactPing, btnIniciar, btnDetener, progress, volume, sonidos[0]);

            //340480_ATf movistar club
            threads[0] = new Thread(runClass);
            threads[0].start();

        } catch (Exception n){
            System.out.println("ERROR ejecutar Ping: " + n.getMessage());
        }
    }

    public  void ejecutarMultiPing(int id, TextField _txtIP, Button _btnIniciar, Button _btnDetener,
                                    RadioButton _radBtn, TextField _nomIp, Text _txtError, Button _pos, Button _cont) {
        try {

            if (threads[id] != null) threads[id].interrupt();
            // prepara el comando CMD
            String cmd = "ping " + _txtIP.getText() + (_radBtn.isSelected() ? " -t " : " ");
             r = Runtime.getRuntime();
             processes[id] = r.exec(cmd);


            sonidos[id] = new Sonido();


            Detener detener = new Detener( _btnIniciar, _btnDetener, _pos);
            // desactiva los elementos
            DesactVentPing desactVentPing = new DesactVentPing(_txtIP, _radBtn, _nomIp);

            // ejecuta el hilo
            MiHilo miHilo = new MiHilo(processes[id], _txtIP, detener, desactVentPing, _nomIp, _txtError, volume, sonidos[id], _cont);
            threads[id]= new Thread(miHilo);
            threads[id].start();
            desactFilaMultiPing(_nomIp, _txtIP,_radBtn, _btnIniciar, _btnDetener, _pos);

        } catch (Exception n){
            System.out.println("ERROR ejecutar Multi Ping: " + n.getMessage());
        }
    }

    public void desactFilaMultiPing(TextField _nomIp, TextField _txtIP, RadioButton _radBtn, Button _btnIniciar, Button _btnDetener, Button _pos){
        _nomIp.setDisable(true);
        _txtIP.setDisable(true);
        _radBtn.setDisable(true);
        _btnIniciar.setDisable(true);
        _btnDetener.setDisable(false);
        _pos.setDisable(true);
    }

    public void iniciarTodoMultiPing(){
        Platform.runLater(() -> btnTodos(true));
        for( int i = 1; i < threads.length; i++){
            if (threads[i] == null || !threads[i].isAlive()){
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
            case 1 -> ejecutarMultiPing(id, txtIP1, btnIniciar1, btnDetener1,  radBtn_t1, nomIp1, txtError1, pos1, cont1);
            case 2 -> ejecutarMultiPing(id, txtIP2, btnIniciar2, btnDetener2,  radBtn_t2, nomIp2, txtError2, pos2, cont2);
            case 3 -> ejecutarMultiPing(id, txtIP3, btnIniciar3, btnDetener3,  radBtn_t3, nomIp3, txtError3, pos3, cont3);
            case 4 -> ejecutarMultiPing(id, txtIP4, btnIniciar4, btnDetener4,  radBtn_t4, nomIp4, txtError4, pos4, cont4);
            case 5 -> ejecutarMultiPing(id, txtIP5, btnIniciar5, btnDetener5,  radBtn_t5, nomIp5, txtError5, pos5, cont5);
            case 6 -> ejecutarMultiPing(id, txtIP6, btnIniciar6, btnDetener6,  radBtn_t6, nomIp6, txtError6, pos6, cont6);
            case 7 -> ejecutarMultiPing(id, txtIP7, btnIniciar7, btnDetener7,  radBtn_t7, nomIp7, txtError7, pos7, cont7);
            case 8 -> ejecutarMultiPing(id, txtIP8, btnIniciar8, btnDetener8,  radBtn_t8, nomIp8, txtError8, pos8, cont8);
            case 9 -> ejecutarMultiPing(id, txtIP9, btnIniciar9, btnDetener9,  radBtn_t9, nomIp9, txtError9, pos9, cont9);
            case 10 -> ejecutarMultiPing(id, txtIP10, btnIniciar10, btnDetener10, radBtn_t10, nomIp10, txtError10, pos10, cont10);
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
            sendAlert("Error Numerico","No es un numero entero");
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

                    URL url_name = new URI("http://myexternalip.com/raw").toURL();
                    BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
                    // reads system IPAddress
                    txtIpPublic.setText( sc.readLine().trim());
                } catch (IOException | URISyntaxException e) {
                    sendAlert("No se obtener IP Publica: " , e.getMessage());
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

        cargarIPS();


    }

    public void abrirLog(){
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(new File( "LOG\\" + LocalDate.now().getYear()  + " " + LocalDate.now().getMonth() + "\\TEP " + LocalDate.now().getYear() + " " +  LocalDate.now().getMonth() + " " +  LocalDate.now().getDayOfMonth() + ".log"));
        } catch (IOException | IllegalArgumentException e) {
            sendAlert("ERROR Archivo", "No se encuentra archivo: " + e.getMessage());
        }
    }

    public void abrirCarpetaLog(){
        File rutaCarpetaLog = new File("LOG");
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(rutaCarpetaLog);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendAlert(String title, String cont){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(cont);
        alert.showAndWait();
    }

    public void cargarIPS(){

        Basesita basesita = new Basesita();
        List<NomNumIp> listaIPs = basesita.getNomNumIP();

        TextField[] txtIP = new TextField[10];
        txtIP[0] = txtIP1;
        txtIP[1] = txtIP2;
        txtIP[2] = txtIP3;
        txtIP[3] = txtIP4;
        txtIP[4] = txtIP5;
        txtIP[5] = txtIP6;
        txtIP[6] = txtIP7;
        txtIP[7] = txtIP8;
        txtIP[8] = txtIP9;
        txtIP[9] = txtIP10;
        TextField[] txtNom = new TextField[10];
        txtNom[0] = nomIp1;
        txtNom[1] = nomIp2;
        txtNom[2] = nomIp3;
        txtNom[3] = nomIp4;
        txtNom[4] = nomIp5;
        txtNom[5] = nomIp6;
        txtNom[6] = nomIp7;
        txtNom[7] = nomIp8;
        txtNom[8] = nomIp9;
        txtNom[9] = nomIp10;
        for (int i = 0; i < listaIPs.size(); i++) {
            txtNom[i].setText(listaIPs.get(i).getNombre());
            txtIP[i].setText(listaIPs.get(i).getIp());
        }

    }


    public void guardaIP(MouseEvent mouseEvent){
        Basesita basesita = new Basesita();
        String event = mouseEvent.getSource().toString();


        if (event.contains("pos1")){
            Platform.runLater(()->{
                if (isValidIp(txtIP1.getText())){
                    if (basesita.updateIps( nomIp1.getText(), txtIP1.getText(), 1)){
                        txtError1.setVisible(true);
                        txtError1.setText("guardado Correctamente");
                    }

                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");

                });

        }
        if (event.contains("pos2")){
            Platform.runLater(()-> {
                if (isValidIp(txtIP2.getText())){
                    if (basesita.updateIps(nomIp2.getText(), txtIP2.getText(), 2)) {
                        txtError2.setVisible(true);
                        txtError2.setText("guardado Correctamente");

                    }
                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");
            });
        }
        if (event.contains("pos3")){
            Platform.runLater(()-> {
                if (isValidIp(txtIP3.getText())){
                    if (basesita.updateIps(nomIp3.getText(), txtIP3.getText(), 2)) {
                        txtError3.setVisible(true);
                        txtError3.setText("guardado Correctamente");
                    }
                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");
            });
        }
        if (event.contains("pos4")){
            Platform.runLater(()-> {
                if (isValidIp(txtIP4.getText())){
                    if (basesita.updateIps(nomIp4.getText(), txtIP4.getText(), 4)) {
                        txtError4.setVisible(true);
                        txtError4.setText("guardado Correctamente");
                    }
                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");
            });
        }
        if (event.contains("pos5")){
            Platform.runLater(()-> {
                if (isValidIp(txtIP5.getText())){
                    if (basesita.updateIps(nomIp5.getText(), txtIP5.getText(), 5)) {
                        txtError5.setVisible(true);

                        txtError5.setText("guardado Correctamente");
                    }
                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");
            });
        }
        if (event.contains("pos6")){
            Platform.runLater(()-> {
                if (isValidIp(txtIP6.getText())){
                    if (basesita.updateIps(nomIp6.getText(), txtIP6.getText(), 6)) {
                        txtError6.setVisible(true);

                        txtError6.setText("guardado Correctamente");
                    }
                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");
            });
        }
        if (event.contains("pos7")){
            Platform.runLater(()-> {
                if (isValidIp(txtIP7.getText())){
                    if (basesita.updateIps(nomIp7.getText(), txtIP7.getText(), 7)) {
                        txtError7.setVisible(true);

                        txtError7.setText("guardado Correctamente");
                    }
                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");
            });
        }
        if (event.contains("pos8")){
            Platform.runLater(()-> {
                if (isValidIp(txtIP8.getText())){
                    if (basesita.updateIps(nomIp8.getText(), txtIP8.getText(), 8)) {
                        txtError8.setVisible(true);

                        txtError8.setText("guardado Correctamente");
                    }
                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");
            });
        }
        if (event.contains("pos9")){
            Platform.runLater(()-> {
                if (isValidIp(txtIP9.getText())){
                    if (basesita.updateIps(nomIp9.getText(), txtIP9.getText(), 9)) {
                        txtError9.setVisible(true);

                        txtError9.setText("guardado Correctamente");
                    }
                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");
            });
        }
        if (event.contains("pos10")){
            Platform.runLater(()-> {
                if (isValidIp(txtIP10.getText())){
                    if (basesita.updateIps(nomIp10.getText(), txtIP10.getText(), 10)) {
                        txtError10.setVisible(true);

                        txtError10.setText("guardado Correctamente");
                    }
                }else sendAlert("ERROR Dir IPv4","Formato de Direccion IPV4 no valida");
            });
        }

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
        if (event.contains("btnIniciar10")){
            altaHilos(10);
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
        if (event.contains("btnDetener10")){
            btnIniciar10.setDisable(false);
            btnDetener10.setDisable(true);
            //progress9.setVisible(false);
            threads[10].interrupt();
            processes[10].destroy();
        }


    }
}