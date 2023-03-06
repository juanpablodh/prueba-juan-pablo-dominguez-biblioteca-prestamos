package com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.engimadevelopers.apiprestamoslibros.prestamo.domain.model.PrestamoModel;
import com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.entity.PrestamoEntity;

@Mapper
public interface PrestamoMapper {

    PrestamoMapper INSTANCE = Mappers.getMapper(PrestamoMapper.class);

    PrestamoModel mapToPrestamoModel(PrestamoEntity prestamoEntity);

    PrestamoEntity mapToPrestamoEntity(PrestamoModel prestamoModel);

    List<PrestamoModel> mapToPrestamoListModel(List<PrestamoEntity> prestamoListEntity);

    List<PrestamoEntity> mapToPrestamoListEntity(List<PrestamoModel> prestamoListModel);

}
