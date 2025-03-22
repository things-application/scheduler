package com.things.scheduler.infrastructure.expection;

import feign.RetryableException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationResourceAdvice {


    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(ConflictException cause) {
        return new ApiError(
                cause.getClass().getSimpleName(),
                cause.getMessage(),
                HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleUnauthorizedAccessException(UnauthorizedAccessException cause) {
        return new ApiError(
                cause.getClass().getSimpleName(),
                cause.getMessage(),
                HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // Expired token deve retornar 401
    public ApiError handleExpiredJwtException(ExpiredJwtException cause) {
        return new ApiError(
                cause.getClass().getSimpleName(),
                "Seu token JWT expirou. Faça login novamente.",
                HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(RetryableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ApiError handleRetryableException(RetryableException cause) {
        return new ApiError(
                cause.getClass().getSimpleName(),
                cause.getMessage(),
                HttpStatus.SERVICE_UNAVAILABLE.value()
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleResourceNotFoundException(ResourceNotFoundException cause) {
        return new ApiError(
                cause.getClass().getSimpleName(),
                cause.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleRuntimeException(RuntimeException cause) {
        return new ApiError(
                cause.getClass().getSimpleName(),
                cause.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
