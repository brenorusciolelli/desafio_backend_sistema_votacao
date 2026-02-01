package com.desafio.teste.desafiobackend.model.repository;

import com.desafio.teste.desafiobackend.model.entity.SessaoVotacaoEntity;
import com.desafio.teste.desafiobackend.model.entity.VoteValueEntity;
import com.desafio.teste.desafiobackend.model.enums.VoteValue;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<VoteValueEntity, UUID> {
  long countBySessionAndVoteValue(SessaoVotacaoEntity session, VoteValue value);
  boolean existsByAssociate_IdAndSession_Id(UUID associateId, UUID sessionId);
}
