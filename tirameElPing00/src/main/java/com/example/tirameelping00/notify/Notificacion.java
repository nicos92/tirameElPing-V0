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

    public void sendNotifyFail(String ip){
        service.postNotification("Fallo en la Red", "revise la IP: " + ip, DesktopNotify.FAIL,
                DesktopNotify.LEFT_TO_RIGHT, 4000L, "light");

    }

    public void sendNotifyOk(String ip){
        service.postNotification("Conexion establecida", "Con la IP: " + ip, DesktopNotify.SUCCESS,
                DesktopNotify.LEFT_TO_RIGHT, 5000L, "light");
    }

    public void sendNotifyInsccesible(String ip){
        service.postNotification(("Inaccesible"), "No se Puede Acceder a la Direccion: " + ip,
                DesktopNotify.WARNING, DesktopNotify.LEFT_TO_RIGHT, 5000L, "light");
    }

    public void sendEndNotify() {
        service.postNotification("Fin de Ping", "", DesktopNotify.INFORMATION, DesktopNotify.LEFT_TO_RIGHT, 5000L,
                "light");
    }
}
