package com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.engimadevelopers.apiprestamoslibros.prestamo.domain.exceptions.PrestamoBussinesExceptions;
import com.engimadevelopers.apiprestamoslibros.prestamo.domain.model.PrestamoModel;
import com.engimadevelopers.apiprestamoslibros.prestamo.domain.model.enums.TipoUsuarioEnum;
import com.engimadevelopers.apiprestamoslibros.prestamo.domain.ports.PrestamoServicePort;
import com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.adapters.PrestamoJpaAdapter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoServicePort {

    private final PrestamoJpaAdapter prestamoJpaAdapter;

    @Override
    public List<PrestamoModel> obtenerTodos() {

        List<PrestamoModel> prestamos = this.prestamoJpaAdapter.obtenerTodos();

        if (prestamos.isEmpty()) {
            throw new PrestamoBussinesExceptions("No existen prestamos registrados", HttpStatus.NOT_FOUND);
        }

        return prestamos;
    }

    public List<PrestamoModel> obtenerTodosActivos() {
        List<PrestamoModel> prestamos = this.prestamoJpaAdapter.obtenerTodosActivos();

        if (prestamos.isEmpty()) {
            throw new PrestamoBussinesExceptions("No existen prestamos activos registrados", HttpStatus.NOT_FOUND);
        }

        return prestamos;
    }

    @Override
    public PrestamoModel obtenerPorId(Long id) {
        PrestamoModel prestamoModel = this.prestamoJpaAdapter.obtenerPorId(id);
        if (prestamoModel == null) {
            throw new PrestamoBussinesExceptions("No existe el prestamo", HttpStatus.NOT_FOUND);
        }
        return prestamoModel;
    }

    @Override
    public PrestamoModel crear(PrestamoModel prestamoModel) {

        // Validaciones
        PrestamoModel.validarTipoUsuario(prestamoModel.getTipoUsuario());

        prestamoModel.setFechaMaximaDevolucion(PrestamoModel.calcularFechaMaxima(prestamoModel.getTipoUsuario()));

        // Valida si el libro ya fue prestado
        if (this.prestamoJpaAdapter.obtenerPorIsbn(prestamoModel.getIsbn()) != null) {
            throw new PrestamoBussinesExceptions(
                    "El libro con ISBN " + prestamoModel.getIsbn()
                            + " ya se encuentra prestado, deberas buscar otro libro",
                    HttpStatus.BAD_REQUEST);
        }

        // Valido si el usuario invitado puede prestar
        if (prestamoModel.getTipoUsuario() == TipoUsuarioEnum.Invitado.getValue()) {

            // Obtenemos los prestamos de ese usuario
            List<PrestamoModel> prestamosUsuario = this.prestamoJpaAdapter
                    .obtenerPorIdentificacionUsuario(prestamoModel.getIdentificacionUsuario());

            // Se valida si hay alguno pendiente por entregar
            if (prestamosUsuario.stream()
                    .anyMatch((prestamo -> PrestamoModel.estaPendiente(prestamo.getFechaMaximaDevolucion())))) {

                throw new PrestamoBussinesExceptions(
                        "El usuario con identificación " + prestamoModel.getIdentificacionUsuario()
                                + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo",
                        HttpStatus.BAD_REQUEST);
            } else {
                return this.prestamoJpaAdapter.crear(prestamoModel);
            }

        }
        return this.prestamoJpaAdapter.crear(prestamoModel);

    }

    @Override
    public PrestamoModel eliminarPorId(Long id) {
        PrestamoModel prestamoModel = this.obtenerPorId(id);
        this.prestamoJpaAdapter.eliminarPorId(id);
        return prestamoModel;
    }

    @Override
    public PrestamoModel modificar(PrestamoModel prestamoModel) {
        return this.crear(prestamoModel);
    }

}
