package com.desafio.teste.desafiobackend.controllers;

import com.desafio.teste.desafiobackend.model.entity.PautaEntity;
import com.desafio.teste.desafiobackend.model.request.PautaCreateRequest;
import com.desafio.teste.desafiobackend.service.PautaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pautas")
@RequiredArgsConstructor
public class PautaController {

  private final PautaService pautaService;

  @PostMapping
  public ResponseEntity<PautaEntity> create(@RequestBody @Valid PautaCreateRequest pauta) {
    return new ResponseEntity<>(pautaService.createPauta(pauta),HttpStatus.CREATED);
  }
}

