package com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.entity.PrestamoEntity;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {
    @Query(value = "select * From Prestamo where identificacion_usuario = ?1", nativeQuery = true)
    List<PrestamoEntity> findByIdentificacionUsuario(String identificacionUsuario);

    @Query(value = "select * From Prestamo where isbn = ?1", nativeQuery = true)
    PrestamoEntity findByIsbn(String isbn);

    @Query(value = "SELECT * FROM prestamo WHERE fecha_maxima_devolucion > now()", nativeQuery = true)
    List<PrestamoEntity> obtenerPrestamosActivos();
}
