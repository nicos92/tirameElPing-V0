package com.example.tirameelping00.hilos;


import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.notify.Notificacion;
import com.example.tirameelping00.sonido.Sonido;
import com.example.tirameelping00.ventana.DesactVentPing;

import java.io.*;


public class MiHilo implements Runnable{

    private final Process process;
    private  final String ip;
    private final Detener detener;
    private final DesactVentPing desactVentPing;

    private final Sonido sonido;

    public MiHilo (Process process, String ip,Detener detener, DesactVentPing desactVentPing){
        this.process = process;
        this.ip = ip;
        this.detener = detener;
        this.desactVentPing = desactVentPing;
        this.sonido = new Sonido();
    }

    @Override
    public void run() {

        try{
            BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String inputLine;
            boolean notify = true;
            int i = 0;

            while ((inputLine = lector.readLine()) != null && !Thread.currentThread().isInterrupted()) {
                System.out.println( i++ + " " + lector.readLine());
                notify = sendNotificacion(notify, inputLine, ip);

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
            notificacion.sendNotifyFail(ip);
            sonido.reproducirError();
            return true;
        }
        if ( inputLine.contains("tiempo") && notify){
            notificacion.sendNotifyOk(ip);
            sonido.reproducirOk();
            return false;
        }
        if( inputLine.contains("inaccesible")){
            notificacion.sendNotifyInsccesible(ip);
            sonido.reproducirError();
        }
        if (inputLine.contains("Paquetes")) {
            notificacion.sendEndNotify();
        }
        return notify;
    }
}
