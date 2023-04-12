package com.example.tirameelping00.hilos;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.service.NotifyService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EjecutarPingHilo implements Runnable{

    private final Process process;
    private final List<String> textArea;

    private final String ip;



    public EjecutarPingHilo(Process p, String ip){
        process = p;
        this.ip = ip;
        textArea = new ArrayList<>();

    }
    @Override
    public void run() {

        BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String inputLine;
        boolean notify = true;
        try{

            while ((inputLine = lector.readLine()) != null && !Thread.currentThread().isInterrupted()){
                notify = notificacion(notify, inputLine, ip);
                textArea.add(inputLine + " \n");
                System.out.println( inputLine );
                //detener = onBtnDetener();
            }

        }catch (Exception e){
            System.out.println("Error Run: " + e.getMessage());
        }

    }

    public List<String> getTextArea(){
        return textArea;
    }

    private  boolean notificacion(boolean notify, String inputLine, String ip){

        NotifyService service = NotifyService.get();
        //service.postNotification("Some title", "A message", DesktopNotify.FAIL, DesktopNotify.LEFT_TO_RIGHT, 2000L, "light");

        if (inputLine.contains("Error") || inputLine.contains("agotado")){
            service.postNotification("Fallo en la Red", "revise la IP: " + ip, DesktopNotify.FAIL, DesktopNotify.LEFT_TO_RIGHT, 2000L, "light");
            //DesktopNotify.showDesktopMessage("Fallo en la Red", "revise la IP: " + ip, DesktopNotify.FAIL, 2000L);
            return true;
        }

        if ( inputLine.contains("tiempo") && notify){
            service.postNotification("Conexion establecida", "Con la IP: " + ip, DesktopNotify.SUCCESS, DesktopNotify.LEFT_TO_RIGHT, 5000L, "light");

            //DesktopNotify.showDesktopMessage("Conexion establecida", "conexion establecida con la IP: " + ip, DesktopNotify.SUCCESS, 5000L);
            return false;
        }

        if( inputLine.contains("inaccesible")){
            service.postNotification(("Inaccesible"), "No se Puede Acceder a la Direccion: " + ip,
                    DesktopNotify.WARNING, DesktopNotify.LEFT_TO_RIGHT, 5000L, "light");
        }
        return notify;
    }


}
