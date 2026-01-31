package com.desafio.teste.desafiobackend.model.entity;

import jakarta.persistence.CascadeType;
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
@ToString(exclude = "sessao")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PautaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String titulo;
  private String descricao;

  private LocalDateTime createdAt = LocalDateTime.now();

  @OneToOne(mappedBy = "pauta", cascade = CascadeType.ALL)
  private SessaoVotacaoEntity sessao;
}
