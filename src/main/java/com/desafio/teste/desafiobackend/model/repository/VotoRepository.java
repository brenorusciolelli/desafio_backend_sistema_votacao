package com.desafio.teste.desafiobackend.model.repository;

import com.desafio.teste.desafiobackend.model.entity.SessaoVotacaoEntity;
import com.desafio.teste.desafiobackend.model.entity.VoteValueEntity;
import com.desafio.teste.desafiobackend.model.enums.VoteValue;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<VoteValueEntity, UUID> {
  long countBySessionAndValue(SessaoVotacaoEntity session, VoteValue value);
}
