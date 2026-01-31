package com.desafio.teste.desafiobackend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
public class SessaoVotacaoEntity {

  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne
  private PautaEntity pauta;

  private LocalDateTime start;
  private LocalDateTime end;

  public boolean isOpen() {
    return LocalDateTime.now().isBefore(end);
  }
}

