package com.desafio.teste.desafiobackend.exception;

import java.util.UUID;

public class PautaNotFoundException extends BusinessException {

  public PautaNotFoundException(UUID id) {
    super("Pauta not found. Id: " + id);
  }
}

