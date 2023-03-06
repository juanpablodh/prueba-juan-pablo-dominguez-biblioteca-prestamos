package com.engimadevelopers.apiprestamoslibros.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static LocalDate agregarDiasCalendario(LocalDate fecha, int dias) {
        LocalDate resultado = fecha;
        int diasAgregados = 0;
        while (diasAgregados < dias) {
            resultado = resultado.plusDays(1);
            if (!(resultado.getDayOfWeek() == DayOfWeek.SATURDAY || resultado.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++diasAgregados;
            }
        }
        return resultado;
    }

    public static String formatearFecha(LocalDate fecha, String patron) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);
        return formatter.format(fecha);
    }

    public static boolean estaPendiente(LocalDate fechaMaximaDevolucion) {
        return fechaMaximaDevolucion.isAfter(LocalDate.now());
    }

}
