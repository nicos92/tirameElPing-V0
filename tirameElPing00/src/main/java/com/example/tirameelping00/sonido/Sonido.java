package com.example.tirameelping00.sonido;

import javafx.scene.control.Slider;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sonido {




    private  Clip sonido;

    public  void reproducirError(Slider volume){
        try {

            String path = new File("sonidos\\error.wav").getAbsolutePath();

           // Se obtiene un Clip de sonido
           sonido = AudioSystem.getClip();



            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(path)));
            FloatControl gainControl =
                    (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((float) (-80 + volume.getValue()));
            System.out.println(-80 + volume.getValue());
            // Comienza la reproducción
            sonido.start();

        } catch (Exception e) {
            System.out.println("Error play error.wav: " + e.getMessage());
        }
    }
    public  void reproducirOk(Slider volume){
        try {
            String path = new File("sonidos\\ok.wav").getAbsolutePath();

            // Se obtiene un Clip de sonido
            sonido = AudioSystem.getClip();
            System.out.println(-80 + volume.getValue());
            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(path)));
            FloatControl gainControl =
                    (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((float) (-80 + volume.getValue()));

            // Comienza la reproducción
            sonido.start();
            if (sonido.isOpen()) {
                Thread.sleep(1100);
                sonido.close();
            }

        } catch (Exception e) {
            System.out.println("Error play ok.wav: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public  void closeSonido(){
        sonido.close();
    }

    public  Clip getSonido() {
        return sonido;
    }
}
