package com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Prestamo")
public class PrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(max = 10)
    private String isbn;

    @Size(max = 10)
    @Column(nullable = false)
    private String identificacionUsuario;

    @Column(columnDefinition = "SMALLINT", nullable = false)
    private int tipoUsuario;

    @Column(nullable = false)
    private LocalDate fechaMaximaDevolucion;

}
