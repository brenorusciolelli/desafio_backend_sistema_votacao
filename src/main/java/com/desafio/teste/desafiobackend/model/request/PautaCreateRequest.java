package com.desafio.teste.desafiobackend.model.request;

import jakarta.validation.constraints.NotBlank;

public record PautaCreateRequest(
    @NotBlank(message = "Tittle cannot be null or empty.")
    String title,
    @NotBlank(message = "Description cannot be null or empty")
    String description
) {
}
