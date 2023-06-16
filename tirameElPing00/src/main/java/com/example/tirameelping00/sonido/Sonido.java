package com.example.tirameelping00.sonido;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sonido {
    private Clip sonido;
    private final String path = "D:\\Users\\n.sandoval\\Documents\\tirameElPing-V0\\tirameElPing00\\src\\main\\resources\\com\\example\\tirameelping00\\Sounds";

    public Sonido(){

    }

    public void reproducirError(){
        try {
            // Se obtiene un Clip de sonido
           sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(path + "\\alarma.wav")));

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
            sonido.open(AudioSystem.getAudioInputStream(new File(path + "\\ok.wav")));

            // Comienza la reproducción
            sonido.start();
            while (sonido.isOpen()){
                Thread.sleep(1100);
                Thread.interrupted();

                sonido.close();
            }



        } catch (InterruptedException i){
            Thread.interrupted();
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
