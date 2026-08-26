package com.riwi.talent.exception;

public class EmpleadoYaExisteException extends BusinessException {

    public EmpleadoYaExisteException(String id) {
        super("El empleado con el ID '" + id + "' ya se encuentra registrado.");
    }
}
