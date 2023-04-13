package com.example.tirameelping00.notify;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.service.NotifyService;

public class Notificacion {

    NotifyService service = NotifyService.get();

    public NotifyService getService(){
        return service;
    }

    public void sendNotifyFail(String ip){
        service.postNotification("Fallo en la Red", "revise la IP: " + ip, DesktopNotify.FAIL,
                DesktopNotify.LEFT_TO_RIGHT, 2000L, "light");
    }

    public void sendNotifyOk(String ip){
        service.postNotification("Conexion establecida", "Con la IP: " + ip, DesktopNotify.SUCCESS,
                DesktopNotify.LEFT_TO_RIGHT, 5000L, "light");
    }

    public void sendNotifyInsccesible(String ip){
        service.postNotification(("Inaccesible"), "No se Puede Acceder a la Direccion: " + ip,
                DesktopNotify.WARNING, DesktopNotify.LEFT_TO_RIGHT, 5000L, "light");

    }
}
