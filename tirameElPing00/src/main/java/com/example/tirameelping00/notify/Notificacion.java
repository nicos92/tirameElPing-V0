package com.example.tirameelping00.notify;


import ds.desktop.notify.DesktopNotify;

public class Notificacion {


    public void sendNotifyFail(String ip,  String nomIp){
        DesktopNotify.showDesktopMessage("Fallo en la Red de: " + nomIp.toUpperCase(), "revise la IP: " + ip, DesktopNotify.ERROR, 6000L);
    }

    public void sendNotifyOk(String ip, String nomIp){
        DesktopNotify.showDesktopMessage("Conexion establecida a: " + nomIp.toUpperCase(), "Con la IP: " + ip, DesktopNotify.SUCCESS, 6000L);
    }

    public void sendNotifyInsccesible(String ip, String nomIp){
        DesktopNotify.showDesktopMessage("Inaccesible a: " + nomIp.toUpperCase(), "No se Puede Acceder a la Direccion: " + ip,
                DesktopNotify.WARNING, 6000L);

    }

    public void sendEndNotify(String ip, String nomIp) {
        DesktopNotify.showDesktopMessage("Fin de Ping a: " + nomIp.toUpperCase(), "Con la IP: " + ip, DesktopNotify.INFORMATION, 5000L);
    }

}
