package com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.adapters;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.engimadevelopers.apiprestamoslibros.prestamo.domain.model.PrestamoModel;
import com.engimadevelopers.apiprestamoslibros.prestamo.domain.ports.PrestamoPersitencePort;
import com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.entity.PrestamoEntity;
import com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.mappers.PrestamoMapper;
import com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.repository.PrestamoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrestamoJpaAdapter implements PrestamoPersitencePort {

    private final PrestamoRepository prestamoRepository;

    @Override
    public List<PrestamoModel> obtenerTodos() {
        return PrestamoMapper.INSTANCE
                .mapToPrestamoListModel(this.prestamoRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    @Override
    public PrestamoModel obtenerPorId(Long id) {
        return this.prestamoRepository.findById(id).map(PrestamoMapper.INSTANCE::mapToPrestamoModel).orElse(null);
    }

    @Override
    public PrestamoModel crear(PrestamoModel prestamoModel) {
        PrestamoEntity prestamoEntity = PrestamoMapper.INSTANCE.mapToPrestamoEntity(prestamoModel);
        PrestamoEntity prestamoEntitySaved = this.prestamoRepository.save(prestamoEntity);
        return PrestamoMapper.INSTANCE.mapToPrestamoModel(prestamoEntitySaved);
    }

    @Override
    public void eliminarPorId(Long id) {
        this.prestamoRepository.deleteById(id);
    }

    @Override
    public PrestamoModel modificar(PrestamoModel prestamoModel) {
        return this.modificar(prestamoModel);
    }

    public PrestamoModel obtenerPorIsbn(String isbn) {
        return PrestamoMapper.INSTANCE.mapToPrestamoModel(this.prestamoRepository.findByIsbn(isbn));
    }

    public List<PrestamoModel> obtenerPorIdentificacionUsuario(String indentificacionUsuario) {
        return PrestamoMapper.INSTANCE
                .mapToPrestamoListModel(this.prestamoRepository.findByIdentificacionUsuario(indentificacionUsuario));
    }

    public List<PrestamoModel> obtenerTodosActivos() {
        return PrestamoMapper.INSTANCE
                .mapToPrestamoListModel(this.prestamoRepository.obtenerPrestamosActivos());
    }

}
