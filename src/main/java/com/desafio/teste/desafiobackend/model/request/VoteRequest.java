package com.desafio.teste.desafiobackend.model.request;

import com.desafio.teste.desafiobackend.model.enums.VoteValue;
import java.util.UUID;

public record VoteRequest(
    UUID sessionId,
    String cpf,
    VoteValue value
) {}
