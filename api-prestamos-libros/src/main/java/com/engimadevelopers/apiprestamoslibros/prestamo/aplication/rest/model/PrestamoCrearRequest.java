package com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PrestamoCrearRequest {
    @NotBlank(message = "No puede estar vacio")
    @NotEmpty(message = "Es requerido")
    @Size(max = 10, message = "No puede tener mas 10 digitos")
    private String isbn;

    @NotBlank(message = "No puede estar vacio")
    @NotEmpty(message = "Es requerido")
    @Size(max = 10, message = "No puede tener mas 10 digitos")
    private String identificacionUsuario;

    @NotNull(message = "Es requerido")
    @Digits(integer = 1, fraction = 0, message = "No puede tener mas 1 digito")
    private Integer tipoUsuario;
}
