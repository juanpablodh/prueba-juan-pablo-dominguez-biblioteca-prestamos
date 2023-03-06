package com.engimadevelopers.apiprestamoslibros.prestamo.domain.ports;

import java.util.List;

import com.engimadevelopers.apiprestamoslibros.prestamo.domain.model.PrestamoModel;

public interface PrestamoPersitencePort {

    List<PrestamoModel> obtenerTodos();

    PrestamoModel obtenerPorId(Long id);

    PrestamoModel crear(PrestamoModel prestamoModel);

    void eliminarPorId(Long id);

    PrestamoModel modificar(PrestamoModel prestamoModel);

}
