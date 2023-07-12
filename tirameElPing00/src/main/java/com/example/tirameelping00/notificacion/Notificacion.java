package com.example.tirameelping00.notificacion;


import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.File;

public class Notificacion {

    public static void enviarNoti(String title, String message, String notiType, String animation, double time){

        AnimationType type = AnimationType.valueOf(animation);

        TrayNotification tray = new TrayNotification();

        tray.setAnimationType(type);
        tray.setTray(title, message, NotificationType.valueOf(notiType));

        tray.showAndDismiss(Duration.seconds(time));
    }


    public void  envNoti(String title, String text, String img, String position){
        Image image = new Image(new File("imgs/" + img +".png").toURI().toString());


        Notifications noti = Notifications.create();
        noti.title(title);
        noti.text(text);
        noti.hideAfter(Duration.seconds(5));
        noti.graphic(new ImageView(image));
        noti.darkStyle();
        noti.position(Pos.valueOf(position));

        noti.show();
    }
}
