package com.desafio.teste.desafiobackend.exception;

import java.util.Map;

public record ApiErrorResponse(
    int status,
    String error,
    String path,
    Map<String, String> errors
) {
}
