package com.example.tirameelping00.sonido;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sonido {
    private Clip sonido;


    public void reproducir(){
        try {
            // Se obtiene un Clip de sonido
           sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File("D:\\Users\\n.sandoval\\Documents\\tirameElPing-V0\\tirameElPing00\\src\\main\\resources\\com\\example\\tirameelping00\\Sounds\\alarma.wav")));

            // Comienza la reproducción
            sonido.start();

        } catch (Exception e) {
            System.out.println("ERROR Sonido: " + e.getMessage());
        }
    }

    public void closeSonido(){
        sonido.close();
    }


}
