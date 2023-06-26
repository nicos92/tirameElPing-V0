package com.example.tirameelping00.sonido;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sonido {




    private Clip sonido;

    public void reproducirError(){
        try {

            String path = new File("error.wav").getAbsolutePath();
            System.out.println("error.wav: " + path);
           // Se obtiene un Clip de sonido
           sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(path)));

            // Comienza la reproducción
            sonido.start();

        } catch (Exception e) {
            System.out.println("ERROR Sonido alarma: " + e.getMessage());
        }
    }
    public void reproducirOk(){
        try {
            String path = new File("ok.wav").getAbsolutePath();
            System.out.println("ok.wav: " + path);
            // Se obtiene un Clip de sonido
            sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(path)));

            // Comienza la reproducción
            sonido.start();
            if (sonido.isOpen()) {
                Thread.sleep(1050);
                sonido.close();
            }

        } catch (Exception e) {
            System.out.println("ERROR Sonido OK: " + e.getMessage());
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
