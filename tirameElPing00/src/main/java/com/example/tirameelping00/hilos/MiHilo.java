package com.example.tirameelping00.hilos;


public class MiHilo {
    private final Thread thread = new Thread();

    @Override
    public String toString() {
        return "MiHilo{" +
                "thread=" + thread +
                ", id=" + id +
                '}';
    }

    private final Integer id;

    public MiHilo(Integer id) {
        this.id = id;
    }

    public Thread getThread() {
        return thread;
    }

    public Integer getId() {
        return id;
    }
}
