package com.example.tirameelping00.sonido;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sonido {
    private Clip sonido;
    private final String pathCorto = "..\\tirameElPing00\\src\\main\\resources\\com\\example\\tirameelping00\\Sounds";
    public void reproducirError(){
        try {
            // Se obtiene un Clip de sonido
           sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(pathCorto + "\\alarma.wav")));

            // Comienza la reproducción
            sonido.start();

        } catch (Exception e) {
            System.out.println("ERROR Sonido: " + e.getMessage());
        }
    }
    public void reproducirOk(){
        try {
            // Se obtiene un Clip de sonido
            sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(pathCorto + "\\ok.wav")));

            // Comienza la reproducción
            sonido.start();
            if (sonido.isOpen()) {
                Thread.sleep(1050);
                sonido.close();
            }

        } catch (Exception e) {
            System.out.println("ERROR Sonido: " + e.getMessage());
        }
    }

    public void closeSonido(){
        sonido.close();
    }

    public Clip getSonido() {
        return sonido;
    }
}
