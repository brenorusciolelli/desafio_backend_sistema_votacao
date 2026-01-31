package com.desafio.teste.desafiobackend.model.entity;

import com.desafio.teste.desafiobackend.model.enums.VoteValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"associate_id", "session_id"}))
public class VoteValueEntity {

  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne
  private AssociadoEntity associate;

  @ManyToOne
  private SessaoVotacaoEntity session;

  @Enumerated(EnumType.STRING)
  private VoteValue value;
}

