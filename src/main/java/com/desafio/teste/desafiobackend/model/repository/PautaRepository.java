package com.desafio.teste.desafiobackend.model.repository;

import com.desafio.teste.desafiobackend.model.entity.PautaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<PautaEntity, UUID> {}
