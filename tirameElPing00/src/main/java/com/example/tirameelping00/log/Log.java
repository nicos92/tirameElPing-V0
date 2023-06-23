package com.example.tirameelping00.log;

import com.example.tirameelping00.fechaYhora.FechaYhora;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Log {
    public static void crearArchivoLog(String inputLine, String nomIp, String ip){
        String path =
                "LOG\\TEP " + LocalDate.now().getYear() + " " +  LocalDate.now().getMonth() + " " +  LocalDate.now().getDayOfMonth() + ".txt";

        File rutaCarpetaLog = new File("LOG");
        if (rutaCarpetaLog.mkdir()) {
            try (FileWriter log = new FileWriter(path, true)){
                log.write("\n" + nomIp + " " + ip + " " + FechaYhora.fechaYhoraNow() + " " + inputLine);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }else{
            try (FileWriter log = new FileWriter(path, true)){
                log.write("\n" + nomIp + " " + ip + " " + FechaYhora.fechaYhoraNow() + " " + inputLine);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }
}
