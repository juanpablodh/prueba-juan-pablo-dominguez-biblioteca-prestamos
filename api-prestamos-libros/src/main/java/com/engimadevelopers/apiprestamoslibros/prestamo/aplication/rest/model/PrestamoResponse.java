package com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model;

import lombok.Data;

@Data
public class PrestamoResponse {

    private Long id;

    private String isbn;

    private String identificacionUsuario;

    private int tipoUsuario;

    private String fechaMaximaDevolucion;

}
