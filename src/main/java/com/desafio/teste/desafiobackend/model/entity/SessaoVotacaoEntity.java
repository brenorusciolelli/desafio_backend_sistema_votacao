package com.desafio.teste.desafiobackend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "pauta")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SessaoVotacaoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne
  private PautaEntity pauta;

  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public boolean isOpen() {
    return LocalDateTime.now().isBefore(endTime);
  }
}

