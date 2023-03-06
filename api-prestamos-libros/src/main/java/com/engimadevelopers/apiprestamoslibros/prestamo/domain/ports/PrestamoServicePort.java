package com.engimadevelopers.apiprestamoslibros.prestamo.domain.ports;

import java.util.List;

import com.engimadevelopers.apiprestamoslibros.prestamo.domain.exceptions.PrestamoBussinesExceptions;
import com.engimadevelopers.apiprestamoslibros.prestamo.domain.model.PrestamoModel;

public interface PrestamoServicePort {

    List<PrestamoModel> obtenerTodos();

    PrestamoModel obtenerPorId(Long id) throws PrestamoBussinesExceptions;

    PrestamoModel crear(PrestamoModel prestamoModel);

    PrestamoModel eliminarPorId(Long id) throws PrestamoBussinesExceptions;

    PrestamoModel modificar(PrestamoModel prestamoModel);

}
