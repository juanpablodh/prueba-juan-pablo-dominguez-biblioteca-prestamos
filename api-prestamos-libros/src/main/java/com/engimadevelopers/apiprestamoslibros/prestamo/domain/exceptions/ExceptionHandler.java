package com.engimadevelopers.apiprestamoslibros.prestamo.domain.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = PrestamoBussinesExceptions.class)
    public ResponseEntity<Object> handleConflict(PrestamoBussinesExceptions ex) {
        HttpStatus estado = ex.getHttpStatus();
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(cuerpo, estado);
    }

}
