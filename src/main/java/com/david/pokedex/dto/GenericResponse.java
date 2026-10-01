package com.david.pokedex.dto;

import org.springframework.http.HttpStatus;

public record GenericResponse<T>(
        boolean success,
        int status,
        String message,
        String error,
        T data
) {

    public static <T> GenericResponse<T> ok(HttpStatus status, String message, T data) {
        return new GenericResponse<>(true, status.value(), message, null, data);
    }

    public static <T> GenericResponse<T> error(HttpStatus status, String message, String error) {
        return new GenericResponse<>(false, status.value(), message, error, null);
    }
}
