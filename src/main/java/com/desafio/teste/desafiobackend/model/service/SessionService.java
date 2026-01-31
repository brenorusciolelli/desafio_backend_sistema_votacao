package com.desafio.teste.desafiobackend.model.service;

import com.desafio.teste.desafiobackend.model.entity.PautaEntity;
import com.desafio.teste.desafiobackend.model.entity.SessaoVotacaoEntity;
import com.desafio.teste.desafiobackend.model.repository.PautaRepository;
import com.desafio.teste.desafiobackend.model.repository.SessaoRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

  private final SessaoRepository repository;
  private final PautaRepository  pautaRepository;

  public SessaoVotacaoEntity startSession(UUID pautaId, Long minutos) {
    PautaEntity pauta = pautaRepository.findById(pautaId)
        .orElseThrow();

    SessaoVotacaoEntity sessao = new SessaoVotacaoEntity();
    sessao.setPauta(pauta);
    sessao.setStart(LocalDateTime.now());
    sessao.setEnd(LocalDateTime.now().plusMinutes(
        minutos != null ? minutos : 1
    ));

    return repository.save(sessao);
  }
}

