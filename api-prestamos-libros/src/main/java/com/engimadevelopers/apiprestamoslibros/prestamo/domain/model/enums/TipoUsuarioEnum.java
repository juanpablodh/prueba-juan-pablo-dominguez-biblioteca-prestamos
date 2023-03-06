package com.engimadevelopers.apiprestamoslibros.prestamo.domain.model.enums;

public enum TipoUsuarioEnum {

    Afiliado("Afiliado", 1),
    Empleado("Empleado", 2),
    Invitado("Invitado", 3);

    private final String key;
    private final int value;

    TipoUsuarioEnum(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
