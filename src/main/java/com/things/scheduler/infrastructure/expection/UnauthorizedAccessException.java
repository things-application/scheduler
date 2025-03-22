package com.things.scheduler.infrastructure.expection;

// Define a exceção personalizada para acesso não autorizado
public class UnauthorizedAccessException extends RuntimeException {

    // Construtor que aceita uma mensagem personalizada
    public UnauthorizedAccessException(String message) {
        super(message);
    }

}
