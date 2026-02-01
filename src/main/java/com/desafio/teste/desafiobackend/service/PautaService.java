package com.desafio.teste.desafiobackend.service;

import com.desafio.teste.desafiobackend.model.entity.PautaEntity;
import com.desafio.teste.desafiobackend.model.repository.PautaRepository;
import com.desafio.teste.desafiobackend.model.request.PautaCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaService {

  private final PautaRepository  pautaRepository;

  public PautaEntity createPauta(PautaCreateRequest request) {
    return pautaRepository.save(pautaRequestToEntity(request));
  }

  private PautaEntity pautaRequestToEntity(PautaCreateRequest request) {
    PautaEntity pauta = new PautaEntity();
    pauta.setTitle(request.title());
    pauta.setDescription(request.description());
    return pauta;
  }
}

