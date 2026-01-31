package com.desafio.teste.desafiobackend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
public class PautaEntity {

  @Id
  @GeneratedValue
  private UUID id;

  private String titulo;
  private String descricao;

  private LocalDateTime createdAt = LocalDateTime.now();
}
