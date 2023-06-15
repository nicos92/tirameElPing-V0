package com.example.tirameelping00.notify;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.service.NotifyService;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Objects;

public class Notificacion {



    NotifyService service = NotifyService.get();

    public NotifyService getService(){
        return service;
    }

    public void sendNotifyFail(String ip,  String nomIp){
        service.postNotification("Fallo en la Red de: " + nomIp.toUpperCase(), "revise la IP: " + ip, DesktopNotify.FAIL,
                DesktopNotify.LEFT_TO_RIGHT, 6000L, "light");

    }

    public void sendNotifyOk(String ip, String nomIp){
        service.postNotification("Conexion establecida a: " + nomIp.toUpperCase(), "Con la IP: " + ip, DesktopNotify.SUCCESS,
                DesktopNotify.LEFT_TO_RIGHT, 6000L, "light");
    }

    public void sendNotifyInsccesible(String ip, String nomIp){
        service.postNotification("Inaccesible a: " + nomIp.toUpperCase(), "No se Puede Acceder a la Direccion: " + ip,
                DesktopNotify.WARNING, DesktopNotify.LEFT_TO_RIGHT, 6000L, "light");
    }

    public void sendEndNotify(String ip, String nomIp) {
        service.postNotification("Fin de Ping a: " + nomIp.toUpperCase(), "Con la IP: " + ip, DesktopNotify.INFORMATION,
                DesktopNotify.LEFT_TO_RIGHT, 5000L,
                "light");
    }
}
