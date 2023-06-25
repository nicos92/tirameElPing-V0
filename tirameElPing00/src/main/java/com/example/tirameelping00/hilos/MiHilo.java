package com.example.tirameelping00.hilos;


import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.estilos.Style;
import com.example.tirameelping00.log.Log;
import com.example.tirameelping00.sonido.Sonido;
import com.example.tirameelping00.ventana.DesactVentPing;
import ds.desktop.notify.DesktopNotify;
import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




public class MiHilo implements Runnable{

    private final Process process;
    private  final String ip;
    private final Detener detener;
    private final DesactVentPing desactVentPing;

    private final Sonido sonido;

    private boolean bolLog;
    private final TextField nomIp;

    public MiHilo (Process process, String ip, Detener detener, DesactVentPing desactVentPing, TextField nomIp){
        this.process = process;
        this.ip = ip;
        this.detener = detener;
        this.desactVentPing = desactVentPing;
        this.sonido = new Sonido();
        this.nomIp = nomIp;
    }

    @Override
    public void run() {

        try{
            BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String inputLine;
            boolean notify = true;
            //int i = 0;

            while ((inputLine = lector.readLine()) != null && !Thread.currentThread().isInterrupted()) {

                if (sonido.getSonido() != null)sonido.closeSonido();

                try{
                    notify = sendNotificacion(notify, inputLine);
                }catch (Exception e){
                    System.out.println("ERROR Desktop Notify: " + nomIp + " " + ip + " - " +  e.getMessage());
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



        if (inputLine.contains("Error") || inputLine.contains("agotado")){
            DesktopNotify.showDesktopMessage("Fallo en la Red de: " + nomIp.getText().toUpperCase(), "revise la IP: " + ip, DesktopNotify.FAIL, 5000L);
            sonido.reproducirError();
            styleNomIP(Style.rojoItems());
            Log.crearArchivoLog("Fallo    " + inputLine , nomIp.getText(), ip);
            bolLog = true;
            return true;
        }
        if ( inputLine.contains("tiempo") && notify){
            DesktopNotify.showDesktopMessage("Conexion establecida a: " + nomIp.getText()
                    .toUpperCase(), "Con la IP: " + ip, DesktopNotify.SUCCESS, 5000L);


            sonido.reproducirOk();
            styleNomIP(Style.normalItems());
            if (bolLog){
                bolLog = false;
                Log.crearArchivoLog("Conexion " + inputLine, nomIp.getText(), ip);
            }

            return false;
        }
        if( inputLine.contains("inaccesible")){
            DesktopNotify.showDesktopMessage("Inaccesible a: " + nomIp.getText().toUpperCase(), "No se Puede Acceder a la Direccion: " + ip,
                    DesktopNotify.WARNING, 5000L);

            sonido.reproducirError();
            styleNomIP(Style.rojoItems());
            Log.crearArchivoLog("Inacces. " + inputLine, nomIp.getText(), ip);
            bolLog = true;

        }
        if (inputLine.contains("Paquetes")) {
            DesktopNotify.showDesktopMessage("Fin de Ping a: " + nomIp.getText().toUpperCase(), "Con la IP: " + ip, DesktopNotify.INFORMATION, 4000L);
            styleNomIP(Style.normalItems());
        }
        return notify;
    }

    public void styleNomIP(String estilo){
        nomIp.setStyle(estilo);
        nomIp.setStyle(estilo);
    }




}
