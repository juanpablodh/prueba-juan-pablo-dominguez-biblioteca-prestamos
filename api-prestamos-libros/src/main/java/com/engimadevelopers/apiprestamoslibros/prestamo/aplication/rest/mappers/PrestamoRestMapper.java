package com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model.PrestamoCrearRequest;
import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model.PrestamoCrearResponse;
import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model.PrestamoResponse;
import com.engimadevelopers.apiprestamoslibros.prestamo.domain.model.PrestamoModel;

@Mapper
public interface PrestamoRestMapper {

    PrestamoRestMapper INSTANCE = Mappers.getMapper(PrestamoRestMapper.class);

    @Mapping(target = "fechaMaximaDevolucion", dateFormat = "dd/MM/yyyy")
    PrestamoResponse mapToPrestamoResponse(PrestamoModel prestamoModel);

    @Mapping(target = "fechaMaximaDevolucion", dateFormat = "dd/MM/yyyy")
    PrestamoCrearResponse mapToPrestamoCrearResponse(PrestamoModel prestamoModel);

    PrestamoModel mapToPrestamoModel(PrestamoCrearRequest prestamoCrearRequest);

    @Mapping(target = "fechaMaximaDevolucion", dateFormat = "dd/MM/yyyy")
    List<PrestamoResponse> mapToPrestamoListResponse(List<PrestamoModel> prestamoListModel);

}
