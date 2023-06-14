package com.example.tirameelping00.hilos;


import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.fechaYhora.FechaYhora;
import com.example.tirameelping00.notify.Notificacion;
import com.example.tirameelping00.sonido.Sonido;
import com.example.tirameelping00.ventana.DesactVentPing;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MiHilo implements Runnable{

    private  Integer id;
    private Process process;
    private  String ip;
    private  Detener detener;
    private DesactVentPing desactVentPing;

    private Sonido sonido;

    public MiHilo (Process process, String ip,Detener detener, DesactVentPing desactVentPing){
        this.process = process;
        this.ip = ip;
        this.detener = detener;
        this.desactVentPing = desactVentPing;
        this.sonido = new Sonido();
    }


    public void addParametros(  Process process, String ip, Detener detener, DesactVentPing desactVentPing) {
        this.process = process;
        this.ip = ip;
        this.detener = detener;
        this.desactVentPing = desactVentPing;
    }

    @Override
    public String toString() {
        return "MiHilo{" +
                "id=" + id +
                '}';
    }

    public MiHilo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    @Override
    public void run() {

        try{
            BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String inputLine;
            boolean notify = true;



            while ((inputLine = lector.readLine()) != null && !Thread.currentThread().isInterrupted()) {

                notify = sendNotificacion(notify, inputLine, ip);

            }

            detener.sendBtnDetener();
            desactVentPing.desactItemsPing(false);

        }catch (IOException e){
            System.out.println("Error Run: " + e.getMessage());
        }

    }

    private  boolean sendNotificacion(boolean notify, String inputLine, String ip){

        Notificacion notificacion = new Notificacion();

        if (inputLine.contains("Error") || inputLine.contains("agotado")){
            notificacion.sendNotifyFail(ip);
            sonido.reproducir();
            return true;
        }
        if ( inputLine.contains("tiempo") && notify){
            notificacion.sendNotifyOk(ip);
            return false;
        }
        if( inputLine.contains("inaccesible")){
            notificacion.sendNotifyInsccesible(ip);
            sonido.reproducir();
        }
        if (inputLine.contains("Paquetes")) {
            notificacion.sendEndNotify();
        }
        return notify;
    }
}
