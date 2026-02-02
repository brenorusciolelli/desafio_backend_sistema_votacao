package com.desafio.teste.desafiobackend.exception;

import java.util.UUID;

public class SessaoNotFoundException extends BusinessException {

  public SessaoNotFoundException(UUID pautaId) {
    super("Sessão not found for pauta id: " + pautaId);
  }
}

