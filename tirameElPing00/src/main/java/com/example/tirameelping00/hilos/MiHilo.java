package com.example.tirameelping00.hilos;


import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.log.Log;
import com.example.tirameelping00.notify.Notificacion;
import com.example.tirameelping00.sonido.Sonido;
import com.example.tirameelping00.ventana.DesactVentPing;
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
    private final String nomIp;

    public MiHilo (Process process, String ip, Detener detener, DesactVentPing desactVentPing, TextField nomIp){
        this.process = process;
        this.ip = ip;
        this.detener = detener;
        this.desactVentPing = desactVentPing;
        this.sonido = new Sonido();
        this.nomIp = nomIp.getText();
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

                notify = sendNotificacion(notify, inputLine);

            }

            if (sonido.getSonido() != null){
                sonido.closeSonido();


            }
            detener.sendBtnDetenerMulti();
            desactVentPing.desactItemsPingMulti(false);

        }catch (IOException e){
            System.out.println("Error Run: " + e.getMessage());
        }

    }

    private  boolean sendNotificacion(boolean notify, String inputLine){

        Notificacion notificacion = new Notificacion();

        if (inputLine.contains("Error") || inputLine.contains("agotado")){
            notificacion.sendNotifyFail(ip, nomIp);
            Log.crearArchivoLog("Fallo    " + inputLine , nomIp, ip);
            bolLog = true;
            desactVentPing.rojoItems();
            sonido.reproducirError();
            return true;
        }
        if ( inputLine.contains("tiempo") && notify){
            notificacion.sendNotifyOk(ip, nomIp);
            desactVentPing.normalItems();
            sonido.reproducirOk();
            if (bolLog){
                bolLog = false;
                Log.crearArchivoLog("Conexion " + inputLine, nomIp, ip);
            }
            return false;
        }
        if( inputLine.contains("inaccesible")){
            notificacion.sendNotifyInsccesible(ip, nomIp);

            sonido.reproducirError();
            Log.crearArchivoLog("Inacces. " + inputLine, nomIp, ip);
            bolLog = true;
        }
        if (inputLine.contains("Paquetes")) {
            notificacion.sendEndNotify(ip, nomIp);
        }
        return notify;
    }





}
