package com.example.tirameelping00.sonido;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sonido {




    private Clip sonido;

    public void reproducirError(){
        try {

            String path = new File("sonidos\\error.wav").getAbsolutePath();

           // Se obtiene un Clip de sonido
           sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(path)));

            // Comienza la reproducción
            sonido.start();

        } catch (Exception e) {

        }
    }
    public void reproducirOk(){
        try {
            String path = new File("sonidos\\ok.wav").getAbsolutePath();

            // Se obtiene un Clip de sonido
            sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(path)));

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

    public void closeSonido(){
        sonido.close();
    }

    public Clip getSonido() {
        return sonido;
    }
}
