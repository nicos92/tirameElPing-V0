package com.example.tirameelping00.hilos;

import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.fechaYhora.FechaYhora;
import com.example.tirameelping00.notify.Notificacion;
import com.example.tirameelping00.sonido.Sonido;
import com.example.tirameelping00.ventana.DesactVentPing;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EjecutarPingHilo implements Runnable{


    private final TextArea txtAreaSalida;
    private final Process process;
    private final String ip;
    private final TextField txtRutaArchivo;
    private final boolean bool;
    private final Sonido sonido;
    private final Detener detener;
    private final DesactVentPing desactVentPing;

    private  final Thread thread;



    public EjecutarPingHilo(Process p, String ip, boolean selected, TextArea txtAreaSalida, TextField txtRutaArchivo, Detener detener, DesactVentPing desactVentPing,Thread thread){
        this.process = p;
        this.ip = ip;
        this.bool = selected;
        this.txtAreaSalida = txtAreaSalida;
        this.txtRutaArchivo = txtRutaArchivo;
        this.detener = detener;
        this.desactVentPing = desactVentPing;
        this.thread = thread;
        sonido = new Sonido();
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

            while ((inputLine = lector.readLine()) != null && !Thread.currentThread().isInterrupted()) {

                FechaYhora fechaYhora = new FechaYhora();
                String txt = fechaYhora + " " + inputLine + " \n ";
                System.out.println(txt);

                if (bool) {
                    array.add(txt);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                    PrintWriter out = new PrintWriter(bw);
                    for (String text : array) {
                        out.write(text);
                    }
                    out.close();
                }

                txtAreaSalida.setText(txtAreaSalida.getText() + txt);

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

    private  String getNameFile(){
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
        return "K:\\tirameElPing (" + cont + ").txt";
    }


}
