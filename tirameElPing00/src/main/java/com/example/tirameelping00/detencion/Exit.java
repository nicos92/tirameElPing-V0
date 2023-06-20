package com.example.tirameelping00.detencion;

import javafx.application.Platform;

public class Exit {

    private final Thread[] threads;
    private final Process[] processes;

    public Exit(Thread[] threads, Process[] processes) {
        this.threads = threads;
        this.processes = processes;
    }

    public void closeThreadsProcesses(){
        for (Thread t : threads){
            if (t !=  null && t.isAlive()){
                t.interrupt();
            }
        }
        for (Process p: processes){
            if (p != null){
                p.destroy();
            }
        }
    }

    public void exit(){
        closeThreadsProcesses();
        Platform.exit();
        System.exit(0);
    }
}
