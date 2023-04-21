package com.example.tirameelping00.hilos;

import com.example.tirameelping00.notify.Notificacion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EjecutarPingHilo implements Runnable{


    private final TextArea txtAreaSalida;
    private final Process process;
    private final String ip;
    private final TextField txtRutaArchivo;
    private final boolean bool;


    public EjecutarPingHilo(Process p, String ip, boolean selected, TextArea txtAreaSalida, TextField txtRutaArchivo){
        this.process = p;
        this.ip = ip;
        this.bool = selected;
        this.txtAreaSalida = txtAreaSalida;
        this.txtRutaArchivo = txtRutaArchivo;
    }
    @Override
    public void run() {


        try{
            txtAreaSalida.setText("");
            BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String inputLine;
            boolean notify = true;
            List<String> array = new ArrayList<>();

            File file = null;
            if (bool) {
                file = new File(getNameFile());
                txtRutaArchivo.setText(getNameFile());
            }

            while ((inputLine = lector.readLine()) != null && !Thread.currentThread().isInterrupted()){
                String hora = String.valueOf(LocalDateTime.now().getHour());
                String min = String.valueOf(LocalDateTime.now().getMinute());
                String seg = String.valueOf(LocalDateTime.now().getSecond());

                String txt =
                        LocalDate.now().getYear() + "-" + LocalDate.now().getMonth() + "-" + LocalDate.now().getDayOfMonth() + " . " + hora + ":" + min + ":" + seg + "  " + inputLine + "\n";
                System.out.println( txt );


                if (bool){
                    array.add(txt);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                    PrintWriter out = new PrintWriter(bw);
                    for (String text : array) {
                        out.write(text);
                    }
                    out.close();
                }

                txtAreaSalida.setText( txtAreaSalida.getText() + txt );

                notify = sendNotificacion(notify, inputLine, ip);
            }
        }catch (Exception e){
            System.out.println("Error Run: " + e.getMessage());
        }

    }




    private  boolean sendNotificacion(boolean notify, String inputLine, String ip){

        Notificacion notificacion = new Notificacion();

        if (inputLine.contains("Error") || inputLine.contains("agotado")){
            notificacion.sendNotifyFail(ip);
            return true;
        }
        if ( inputLine.contains("tiempo") && notify){
            notificacion.sendNotifyOk(ip);
            return false;
        }
        if( inputLine.contains("inaccesible")){
            notificacion.sendNotifyInsccesible(ip);
        }
        return notify;
    }

    private  String getNameFile(){
        int cont = 0;
        File ruta = new File("D:\\");
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
        return "D:\\tirameElPing (" + cont + ").txt";
    }


}
