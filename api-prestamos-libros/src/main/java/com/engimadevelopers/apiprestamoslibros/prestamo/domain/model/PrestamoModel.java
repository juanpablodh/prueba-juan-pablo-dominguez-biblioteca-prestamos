package com.engimadevelopers.apiprestamoslibros.prestamo.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.engimadevelopers.apiprestamoslibros.prestamo.domain.exceptions.PrestamoBussinesExceptions;
import com.engimadevelopers.apiprestamoslibros.prestamo.domain.model.enums.TipoUsuarioEnum;
import com.engimadevelopers.apiprestamoslibros.utils.Utils;

import lombok.Data;

@Data
public class PrestamoModel {

    private Long id;

    private String isbn;

    private String identificacionUsuario;

    private int tipoUsuario;

    private LocalDate fechaMaximaDevolucion;

    public static boolean estaPendiente(LocalDate fechaMaximaDevolucion) {
        return fechaMaximaDevolucion.isAfter(LocalDate.now());
    }

    public static LocalDate calcularFechaMaxima(int tipoUsuario) {

        switch (tipoUsuario) {

            // Afiliado
            case 1:
                return Utils.agregarDiasCalendario(LocalDate.now(), 10);

            // Empleado
            case 2:
                return Utils.agregarDiasCalendario(LocalDate.now(), 8);

            // Invitado
            case 3:
                return Utils.agregarDiasCalendario(LocalDate.now(), 7);

        }

        return Utils.agregarDiasCalendario(LocalDate.now(), 0);

    }

    public static boolean validarTipoUsuario(int tipoUsuario) {
        List<Integer> tipoUsuariosValidos = new ArrayList<Integer>();
        tipoUsuariosValidos.add(TipoUsuarioEnum.Afiliado.getValue());
        tipoUsuariosValidos.add(TipoUsuarioEnum.Empleado.getValue());
        tipoUsuariosValidos.add(TipoUsuarioEnum.Invitado.getValue());

        if (!tipoUsuariosValidos.contains(tipoUsuario)) {
            throw new PrestamoBussinesExceptions("Tipo de usuario no permitido en la biblioteca",
                    HttpStatus.BAD_REQUEST);
        }

        return true;
    }

}
