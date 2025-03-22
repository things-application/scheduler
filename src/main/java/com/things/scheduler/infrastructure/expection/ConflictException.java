package com.things.scheduler.infrastructure.expection;

// Define uma exceção personalizada para conflitos
public class ConflictException extends RuntimeException {
    // Construtor que aceita uma mensagem personalizada
    public ConflictException(String message) {
        super(message);
    }

}