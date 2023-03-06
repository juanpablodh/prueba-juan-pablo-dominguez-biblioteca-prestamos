package com.engimadevelopers.apiprestamoslibros.prestamo.domain.exceptions;

import org.springframework.http.HttpStatus;

public class PrestamoBussinesExceptions extends RuntimeException {

    private String mensaje;
    private HttpStatus httpStatus;

    public PrestamoBussinesExceptions(String mensaje, HttpStatus httpStatus) {
        super(mensaje);
        this.mensaje = mensaje;
        this.httpStatus = httpStatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
