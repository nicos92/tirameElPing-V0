package com.example.tirameelping00.hilos;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.service.NotifyService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EjecutarPingHilo implements Runnable{

    private final Process process;
    private final List<String> textArea;
    private final String ip;

    private  boolean bool;


    public EjecutarPingHilo(Process p, String ip){
        process = p;
        this.ip = ip;
        textArea = new ArrayList<>();
        bool = false;
    }
    @Override
    public void run() {

        File file = getNameFile();
        BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String inputLine;
        boolean notify = true;
        List<String> array = new ArrayList<>();
        try{

            while ((inputLine = lector.readLine()) != null && !Thread.currentThread().isInterrupted()){

                System.out.println( inputLine );

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(bw);
                String hora = String.valueOf(LocalDateTime.now().getHour());
                String min = String.valueOf(LocalDateTime.now().getMinute());
                String seg = String.valueOf(LocalDateTime.now().getSecond());
                notify = notificacion(notify, inputLine, ip);
                textArea.add(inputLine + " \n");
                String txt = LocalDate.now().getYear() + "-" + LocalDate.now().getMonth() + "-" + LocalDate.now().getDayOfMonth() + " . " + hora + ":" + min + ":" + seg + "  " + inputLine + " \n";

                array.add(txt);

                for (String text : array) {
                    out.write(text);
                }

                out.close();
            }

        }catch (Exception e){
            System.out.println("Error Run: " + e.getMessage());
        }
        bool = true;

    }

    public boolean getBool(){
        return bool;
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

    private  File getNameFile(){
        int cont = 0;
        File ruta = new File("K:\\");
        String[] nombres = ruta.list();
        assert nombres != null;
        String[] newName;
        for (String name : nombres) {
            if (name.contains("tirameElPing (")) {
                newName = name.split("[(]|[)]" );
                if (Integer.parseInt(newName[1]) > cont){
                    cont = Integer.parseInt(newName[1]);
                }
            }
        }
        cont++;
        return new File("K:\\tirameElPing (" + cont + ").txt");
    }


}
