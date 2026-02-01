package com.desafio.teste.desafiobackend.service;

import com.desafio.teste.desafiobackend.model.entity.AssociadoEntity;
import com.desafio.teste.desafiobackend.model.entity.SessaoVotacaoEntity;
import com.desafio.teste.desafiobackend.model.entity.VoteValueEntity;
import com.desafio.teste.desafiobackend.model.enums.VoteValue;
import com.desafio.teste.desafiobackend.model.repository.AssociadoRepository;
import com.desafio.teste.desafiobackend.model.repository.SessaoRepository;
import com.desafio.teste.desafiobackend.model.repository.VoteRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

  private final VoteRepository   voteRepository;
  private final SessaoRepository sessaoRepository;
  private final AssociadoRepository associadoRepository;

  public VoteValueEntity vote(UUID sessaoId, String cpf, VoteValue valor) {

    SessaoVotacaoEntity sessao = sessaoRepository.findByPauta_Id(sessaoId)
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
    vote.setVoteValue(valor);

    return voteRepository.save(vote);
  }
}

