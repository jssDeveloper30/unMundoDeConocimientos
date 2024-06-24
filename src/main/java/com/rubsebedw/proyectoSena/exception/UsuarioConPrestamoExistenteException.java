package com.rubsebedw.proyectoSena.exception;

public class UsuarioConPrestamoExistenteException extends RuntimeException {

    private final String identificacionUsuario;

    public UsuarioConPrestamoExistenteException(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }
}
