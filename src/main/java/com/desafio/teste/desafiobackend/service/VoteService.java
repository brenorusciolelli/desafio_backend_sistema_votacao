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

    SessaoVotacaoEntity session = sessaoRepository.findByPauta_Id(sessaoId)
        .orElseThrow();

    AssociadoEntity associate = associadoRepository
        .findByCpf(cpf)
        .orElseGet(() -> associadoRepository.save(new AssociadoEntity(null, cpf)));

    verifyIfSessionIsOpen(session);
    verifyIfAssociateAlreadyVotedInThisSession(associate, session);

    VoteValueEntity vote = VoteValueEntity.builder()
        .session(session)
        .associate(associate)
        .voteValue(valor)
        .build();

    return voteRepository.save(vote);
  }

  private void verifyIfSessionIsOpen(SessaoVotacaoEntity session) {
    if (!session.isOpen()) {
      throw new IllegalStateException("Sessão encerrada");
    }
  }
  private void verifyIfAssociateAlreadyVotedInThisSession(AssociadoEntity associate, SessaoVotacaoEntity session) {
    if (voteRepository.existsByAssociate_IdAndSession_Id(
        associate.getId(), session.getId())) {
        throw new IllegalStateException("Associado já votou nesta sessão");
      }
    }
}

