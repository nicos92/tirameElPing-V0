package com.example.tirameelping00.fechaYhora;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class FechaYhora {
    private final int year;
    private final Month month;
    private final int day;
    private final int hour;
    private final int minute;
    private final int second;

    @Override
    public String toString() {
        return  year +
                "-" + month +
                "-" + day +
                " . " + hour +
                ":" + minute +
                ":" + second;
    }

    public FechaYhora() {
        this.year = LocalDate.now().getYear();
        this.month = LocalDate.now().getMonth();
        this.day = LocalDate.now().getDayOfMonth();
        this.hour = LocalDateTime.now().getHour();
        this.minute = LocalDateTime.now().getMinute();
        this.second = LocalDateTime.now().getSecond();
    }
}
