package com.desafio.teste.desafiobackend.model.repository;

import com.desafio.teste.desafiobackend.model.entity.SessaoVotacaoEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<SessaoVotacaoEntity, UUID> {
  Optional<SessaoVotacaoEntity> findByPauta_Id(UUID pautaId);
  boolean existsByPauta_IdAndEndTimeAfter(UUID pautaId, LocalDateTime dateTime);
}
