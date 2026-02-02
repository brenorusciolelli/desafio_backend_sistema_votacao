package com.desafio.teste.desafiobackend.service;

import com.desafio.teste.desafiobackend.exception.BusinessException;
import com.desafio.teste.desafiobackend.exception.PautaNotFoundException;
import com.desafio.teste.desafiobackend.exception.SessaoNotFoundException;
import com.desafio.teste.desafiobackend.model.dto.VoteResultDTO;
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

    PautaEntity pauta = getPautaOrThrow(pautaId);
    validateNoActiveSession(pautaId);

    long duration = resolveDuration(minutos);

    SessaoVotacaoEntity sessao = new SessaoVotacaoEntity();
    sessao.setPauta(pauta);
    sessao.setStartTime(now());
    sessao.setEndTime(now().plusMinutes(duration));

    return repository.save(sessao);
  }

  protected LocalDateTime now() {
    return LocalDateTime.now();
  }


  public VoteResultDTO calculateVotes(UUID pautaId) {
    SessaoVotacaoEntity sessao = getSessaoByPautaOrThrow(pautaId);

    long yes = voteRepository.countBySessionAndVoteValue(sessao, VoteValue.YES);
    long not = voteRepository.countBySessionAndVoteValue(sessao, VoteValue.NOT);

    return new VoteResultDTO(yes, not);
  }

  private PautaEntity getPautaOrThrow(UUID pautaId) {
    return pautaRepository.findById(pautaId)
        .orElseThrow(() -> new PautaNotFoundException(pautaId));
  }

  private SessaoVotacaoEntity getSessaoByPautaOrThrow(UUID pautaId) {
    return repository.findByPauta_Id(pautaId)
        .orElseThrow(() -> new SessaoNotFoundException(pautaId));
  }

  private long resolveDuration(Long minutos) {
    if (minutos == null) return 1;
    if (minutos <= 0) {
      throw new IllegalArgumentException("Duração da sessão deve ser maior que zero");
    }
    return minutos;
  }

  private void validateNoActiveSession(UUID pautaId) {
    if (repository.existsByPauta_IdAndEndTimeAfter(pautaId, LocalDateTime.now())) {
      throw new BusinessException("Já existe uma sessão ativa para esta pauta");
    }
  }

}

