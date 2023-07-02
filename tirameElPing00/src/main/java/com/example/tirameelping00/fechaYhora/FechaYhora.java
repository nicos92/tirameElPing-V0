package com.example.tirameelping00.fechaYhora;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaYhora {

    public static String fechaYhoraNow(){
        LocalDateTime fechaYhoraLog = LocalDateTime.now();
        DateTimeFormatter fechaLog = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
        return fechaYhoraLog.format(fechaLog);
    }
}
