package com.example.tirameelping00.hilos;


import com.example.tirameelping00.detencion.Detener;
import com.example.tirameelping00.ventana.DesactVentPing;

public class MiHilo implements Runnable{

    private final Integer id;
    private Process process;
    private  String ip;
    private  Detener detener;
    private DesactVentPing desactVentPing;


    public void addParametros(  Process process, String ip, Detener detener, DesactVentPing desactVentPing) {
        this.process = process;
        this.ip = ip;
        this.detener = detener;
        this.desactVentPing = desactVentPing;
    }

    @Override
    public String toString() {
        return "MiHilo{" +
                "id=" + id +
                '}';
    }

    public MiHilo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void run() {

    }
}
