package com.example.tirameelping00.hilos;

import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.fechaYhora.FechaYhora;
import com.example.tirameelping00.log.Log;
import com.example.tirameelping00.sonido.Sonido;
import com.example.tirameelping00.ventana.DesactVentPing;
import ds.desktop.notify.DesktopNotify;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    private boolean bolLog;

    public EjecutarPingHilo(Process p, String ip, boolean selected, TextArea txtAreaSalida, TextField txtRutaArchivo, DesactVentPing desactVentPing, Button btnIniciar, Button btnDetener, ProgressIndicator progress, Text txtError){
        this.process = p;
        this.ip = ip;
        this.bool = selected;
        this.txtAreaSalida = txtAreaSalida;
        this.txtRutaArchivo = txtRutaArchivo;
        this.detener = new Detener(btnIniciar,btnDetener,progress, txtError);
        this.desactVentPing = desactVentPing;
        sonido = new Sonido();
    }

    @Override
    public void run() {
        try{
            int i = 0;
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
                i++;

                String txt = FechaYhora.fechaYhoraNow() + "  " + inputLine + " \n ";

                if (bool) {
                    array.add(txt);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
                    PrintWriter out = new PrintWriter(bw);
                    for (String text : array) {
                        out.write(text);
                    }
                    out.close();
                }

                if (i > 100){
                    i=0;
                    txtAreaSalida.setText("");
                }
                Platform.runLater(() -> txtAreaSalida.appendText( txt));

                if (sonido.getSonido() != null)sonido.closeSonido();
                try {
                    notify = sendNotificacion(notify, inputLine, ip);

                }catch (Exception e){
                    System.out.println("ERROR Desktop Notify: " + e.getMessage());
                    Thread.currentThread().interrupt();
                    Thread.currentThread().start();
                }
            }

            if (sonido.getSonido() != null){
                sonido.closeSonido();
            }

            detener.sendBtnDetener();
            desactVentPing.desactItemsPing(false);

        }catch (IOException e){
            System.out.println("Error Run: " + e.getMessage());
        }
    }

    private  boolean sendNotificacion(boolean notify, String inputLine, String ip){

        if (inputLine.contains("Error") || inputLine.contains("agotado")){
            DesktopNotify.showDesktopMessage("Fallo en la Red", "revise la IP: " + ip, DesktopNotify.FAIL, 5000L);
            Log.crearArchivoLog("Fallo    " + inputLine , "Ping: ", ip);
            bolLog = true;
            sonido.reproducirError();
            return true;
        }
        if ( inputLine.contains("tiempo") && notify){
            DesktopNotify.showDesktopMessage("Conexion establecida", "Con la IP: " + ip, DesktopNotify.SUCCESS, 5000L);

            if (bolLog){
                bolLog = false;
                Log.crearArchivoLog("Conexion " + inputLine, "Ping: ", ip);
            }

            sonido.reproducirOk();
            return false;
        }
        if( inputLine.contains("inaccesible")){
            DesktopNotify.showDesktopMessage("Inaccesible", "No se Puede Acceder a la Direccion: " + ip,
                    DesktopNotify.WARNING, 5000L);

            sonido.reproducirError();
            Log.crearArchivoLog("Inacces. " + inputLine, "Ping: ", ip);
            bolLog = true;
        }
        if (inputLine.contains("Paquetes")) {
            DesktopNotify.showDesktopMessage("Fin de Ping", "Con la IP: " + ip, DesktopNotify.INFORMATION, 4000L);

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
