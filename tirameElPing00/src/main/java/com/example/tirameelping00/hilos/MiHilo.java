package com.example.tirameelping00.hilos;


import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.estilos.Style;
import com.example.tirameelping00.log.Log;
import com.example.tirameelping00.sonido.Sonido;
import com.example.tirameelping00.ventana.DesactVentPing;
import ds.desktop.notify.DesktopNotify;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MiHilo implements Runnable{

    private final Process process;
    private  final TextField ip;
    private final Detener detener;
    private final DesactVentPing desactVentPing;
    private final Slider volume;

    private final Sonido sonido;

    private boolean bolLog;
    private final TextField nomIp;

    private final Text txtError;

    private final Lock lock = new ReentrantLock();



    public MiHilo (Process process, TextField ip, Detener detener, DesactVentPing desactVentPing, TextField nomIp,
                   Text txtError, Slider volume, Sonido sonido){
        this.process = process;
        this.ip = ip;
        this.detener = detener;
        this.desactVentPing = desactVentPing;
        this.volume = volume;

        this.sonido = sonido;
        this.nomIp = nomIp;
        this.txtError = txtError;

    }

    @Override
    public void run() {

        try{
            txtError.setVisible(false);
            BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String inputLine;
            boolean notify = true;

            while ((inputLine = lector.readLine()) != null && !Thread.currentThread().isInterrupted()) {

                if (sonido.getSonido() != null)sonido.closeSonido();

                try{


                    notify = sendNotificacion(notify, inputLine);
                }catch (Exception e){
                    Platform.runLater(() -> {
                        txtError.setText("ERROR Desktop Notify: " + nomIp.getText() + " " + ip.getText() + ". Intente Nuevamente");
                        System.out.println("ERROR Desktop Notify: " + nomIp.getText() + " " + ip.getText() + e.getMessage());
                        txtError.setVisible(true);
                    });
                    process.destroy();
                    Thread.currentThread().interrupt();
                }
            }

            if (sonido.getSonido() != null){
                sonido.closeSonido();
            }
            Platform.runLater(() -> {
                detener.sendBtnDetenerMulti();
                desactVentPing.desactItemsPingMulti(false);
                styleNomIP(Style.normalItems());
                //new Alert(Alert.AlertType.INFORMATION, "We just ran some JavaFX code from an AWT MenuItem!").showAndWait();
            });

        }catch (IOException e){
            System.out.println("Error Run: " + e.getMessage());
        }
    }

    private  boolean sendNotificacion(boolean notify, String inputLine){

        lock.lock();
        try {


            if (inputLine.contains("Error") || inputLine.contains("agotado")) {
                DesktopNotify.showDesktopMessage("Fallo en la Red de: " + nomIp.getText().toUpperCase(), "revise la IP: " + ip.getText(), DesktopNotify.FAIL, 5000L);
                reprodicirSonido("sonidos\\error.wav", false);
                styleNomIP(Style.rojoItems());
                Log.crearArchivoLog("Fallo        -    " + inputLine, nomIp.getText(), ip.getText());
                bolLog = true;
                return true;
            }
            if (inputLine.contains("tiempo") && notify) {
                DesktopNotify.showDesktopMessage("Conexion establecida a: " + nomIp.getText()
                        .toUpperCase(), "Con la IP: " + ip.getText(), DesktopNotify.SUCCESS, 5000L);

                reprodicirSonido("sonidos\\ok.wav", true);

                if (bolLog) {
                    styleNomIP(Style.normalItems());
                    bolLog = false;
                    Log.crearArchivoLog("Conexion     -    " + inputLine, nomIp.getText(), ip.getText());
                }

                return false;
            }
            if (inputLine.contains("inaccesible")) {
                DesktopNotify.showDesktopMessage("Inaccesible a: " + nomIp.getText().toUpperCase(), "No se Puede Acceder a la Direccion: " + ip.getText(),
                        DesktopNotify.WARNING, 5000L);

                reprodicirSonido("sonidos\\error.wav", false);
                styleNomIP(Style.rojoItems());
                Log.crearArchivoLog("Inacces.     -    " + inputLine, nomIp.getText(), ip.getText());
                bolLog = true;

            }
            if (inputLine.contains("Paquetes")) {
                DesktopNotify.showDesktopMessage("Fin de Ping a: " + nomIp.getText().toUpperCase(), "Con la IP: " + ip.getText(), DesktopNotify.INFORMATION, 4000L);
                styleNomIP(Style.normalItems());
            }

        }finally {
            lock.unlock();
        }

        return notify;
    }

    public void styleNomIP(String estilo){
        nomIp.setStyle(estilo);
        ip.setStyle(estilo);
    }

    private void reprodicirSonido(String pathname, boolean bol) {
        sonido.selectSonido(new File(pathname));
        sonido.setGainControl(volume.getValue());


        sonido.play(bol);
    }




}
