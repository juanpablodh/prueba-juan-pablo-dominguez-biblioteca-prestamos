package com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.mappers.PrestamoRestMapper;
import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model.PrestamoCrearRequest;
import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model.PrestamoCrearResponse;
import com.engimadevelopers.apiprestamoslibros.prestamo.aplication.rest.model.PrestamoResponse;
import com.engimadevelopers.apiprestamoslibros.prestamo.infrastructure.service.PrestamoServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/prestamo")
public class PrestamoRestController {

    private final PrestamoServiceImpl prestamoServiceImpl;

    public PrestamoRestController(PrestamoServiceImpl prestamoServiceImpl) {
        this.prestamoServiceImpl = prestamoServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> obtenerTodos() {
        return ResponseEntity
                .ok(PrestamoRestMapper.INSTANCE.mapToPrestamoListResponse(this.prestamoServiceImpl.obtenerTodos()));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PrestamoResponse>> obtenerTodosActivos() {
        return ResponseEntity.ok(
                PrestamoRestMapper.INSTANCE.mapToPrestamoListResponse(this.prestamoServiceImpl.obtenerTodosActivos()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity
                .ok(PrestamoRestMapper.INSTANCE.mapToPrestamoResponse(this.prestamoServiceImpl.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<PrestamoCrearResponse> crear(@Valid @RequestBody PrestamoCrearRequest prestamoCrearRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                PrestamoRestMapper.INSTANCE.mapToPrestamoCrearResponse(this.prestamoServiceImpl
                        .crear(PrestamoRestMapper.INSTANCE.mapToPrestamoModel(prestamoCrearRequest))));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PrestamoResponse> eliminar(@PathVariable Long id) {
        return ResponseEntity
                .ok(PrestamoRestMapper.INSTANCE.mapToPrestamoResponse(this.prestamoServiceImpl.eliminarPorId(id)));

    }

}
