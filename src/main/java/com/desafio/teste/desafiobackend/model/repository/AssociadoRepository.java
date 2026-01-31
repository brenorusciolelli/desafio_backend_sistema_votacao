package com.desafio.teste.desafiobackend.model.repository;

import com.desafio.teste.desafiobackend.model.entity.AssociadoEntity;
import com.desafio.teste.desafiobackend.model.entity.PautaEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<AssociadoEntity, UUID> {
  Optional<AssociadoEntity> findByCpf(String cpf);
}
