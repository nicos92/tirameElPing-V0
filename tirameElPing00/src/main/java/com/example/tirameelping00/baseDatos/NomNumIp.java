package com.example.tirameelping00.baseDatos;

public class NomNumIp {
    private String nombre;
    private String ip;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "NomNumIp{" +
                "nombre='" + nombre + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
