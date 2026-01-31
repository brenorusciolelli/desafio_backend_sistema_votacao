package com.desafio.teste.desafiobackend.model.service;

import com.desafio.teste.desafiobackend.model.entity.AssociadoEntity;
import com.desafio.teste.desafiobackend.model.entity.SessaoVotacaoEntity;
import com.desafio.teste.desafiobackend.model.entity.VoteValueEntity;
import com.desafio.teste.desafiobackend.model.enums.VoteValue;
import com.desafio.teste.desafiobackend.model.repository.AssociadoRepository;
import com.desafio.teste.desafiobackend.model.repository.SessaoRepository;
import com.desafio.teste.desafiobackend.model.repository.VotoRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

  private final VotoRepository      votoRepository;
  private final SessaoRepository    sessaoRepository;
  private final AssociadoRepository associadoRepository;

  public void vote(UUID sessaoId, String cpf, VoteValue valor) {

    SessaoVotacaoEntity sessao = sessaoRepository.findById(sessaoId)
        .orElseThrow();

    if (!sessao.isOpen()) {
      throw new IllegalStateException("Sessão encerrada");
    }

    AssociadoEntity associate = associadoRepository
        .findByCpf(cpf)
        .orElseGet(() -> associadoRepository.save(new AssociadoEntity(null, cpf)));

    VoteValueEntity vote = new VoteValueEntity();
    vote.setSession(sessao);
    vote.setAssociate(associate);
    vote.setValue(valor);

    votoRepository.save(vote);
  }
}

