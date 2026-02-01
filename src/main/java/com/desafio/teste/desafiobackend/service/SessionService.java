package com.desafio.teste.desafiobackend.service;

import com.desafio.teste.desafiobackend.model.entity.PautaEntity;
import com.desafio.teste.desafiobackend.model.entity.SessaoVotacaoEntity;
import com.desafio.teste.desafiobackend.model.enums.VoteValue;
import com.desafio.teste.desafiobackend.model.repository.PautaRepository;
import com.desafio.teste.desafiobackend.model.repository.SessaoRepository;
import com.desafio.teste.desafiobackend.model.repository.VoteRepository;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

  private final SessaoRepository repository;
  private final PautaRepository pautaRepository;
  private final VoteRepository  voteRepository;

  public SessaoVotacaoEntity startSession(UUID pautaId, Long minutos) {
    PautaEntity pauta = pautaRepository.findById(pautaId)
        .orElseThrow();

    SessaoVotacaoEntity sessao = new SessaoVotacaoEntity();
    sessao.setPauta(pauta);
    sessao.setStartTime(LocalDateTime.now());
    sessao.setEndTime(LocalDateTime.now().plusMinutes(
        minutos != null ? minutos : 1
    ));

    return repository.save(sessao);
  }

  public Map<String, Long> calculateVotes(UUID id){
    SessaoVotacaoEntity sessao = repository.findByPauta_Id(id)
        .orElseThrow();

    return Map.of(
        "YES", voteRepository.countBySessionAndVoteValue(sessao, VoteValue.YES),
        "NOT", voteRepository.countBySessionAndVoteValue(sessao, VoteValue.NOT)
    );
  }

}

