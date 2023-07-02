package com.example.tirameelping00.sonido;

import javax.sound.sampled.*;
import java.io.File;


public class Sonido {

    private  Clip sonido;
    private FloatControl gainControl;

    public void selectSonido(File url){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            sonido = AudioSystem.getClip();
            sonido.open(audio);
            gainControl= (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            System.out.println("Error selectSonido: " + e.getMessage());
        }
    }

    public void play(boolean bol){
        sonido.setFramePosition(0);
        sonido.start();
        if (bol) {
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public  void closeSonido(){
        sonido.close();
    }

    public  Clip getSonido() {
        return sonido;
    }

   public  void setGainControl(double volume){
        gainControl.setValue(-80 + (float) volume);
    }
}
