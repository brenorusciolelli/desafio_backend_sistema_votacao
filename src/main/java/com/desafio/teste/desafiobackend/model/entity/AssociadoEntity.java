package com.desafio.teste.desafiobackend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
public class AssociadoEntity {

  @Id
  @GeneratedValue
  private UUID id;

  private String cpf;
}

