package com.example.tirameelping00.hilos;


import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.notify.Notificacion;
import com.example.tirameelping00.sonido.Sonido;
import com.example.tirameelping00.ventana.DesactVentPing;
import javafx.scene.control.TextField;

import java.io.*;


public class MiHilo implements Runnable{

    private final Process process;
    private  final String ip;
    private final Detener detener;
    private final DesactVentPing desactVentPing;

    private final Sonido sonido;

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

                notify = sendNotificacion(notify, inputLine, ip);

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

    private  boolean sendNotificacion(boolean notify, String inputLine, String ip){

        Notificacion notificacion = new Notificacion();

        if (inputLine.contains("Error") || inputLine.contains("agotado")){
            notificacion.sendNotifyFail(ip, nomIp);

            sonido.reproducirError();
            return true;
        }
        if ( inputLine.contains("tiempo") && notify){
            notificacion.sendNotifyOk(ip, nomIp);

            sonido.reproducirOk();
            return false;
        }
        if( inputLine.contains("inaccesible")){
            notificacion.sendNotifyInsccesible(ip, nomIp);

            sonido.reproducirError();
        }
        if (inputLine.contains("Paquetes")) {
            notificacion.sendEndNotify(ip, nomIp);
        }
        return notify;
    }
}
