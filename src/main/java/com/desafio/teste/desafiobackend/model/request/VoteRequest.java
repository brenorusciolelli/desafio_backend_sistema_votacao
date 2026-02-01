package com.desafio.teste.desafiobackend.model.request;

import com.desafio.teste.desafiobackend.model.enums.VoteValue;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record VoteRequest(
    @NotBlank(message = "SessionId is required and cannot be empty or null.")
    UUID sessionId,
    @NotBlank(message = "CPF is required and cannot be empty or null.")
    String cpf,
    @NotBlank(message = "Vote is required and cannot be empty or null.")
    VoteValue value
) {}
