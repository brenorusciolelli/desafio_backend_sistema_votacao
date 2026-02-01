package com.desafio.teste.desafiobackend.controllers;

import com.desafio.teste.desafiobackend.model.entity.SessaoVotacaoEntity;
import com.desafio.teste.desafiobackend.service.SessionService;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

  private final SessionService   sessaoService;

  @PostMapping("/start/{id}")
  public ResponseEntity<SessaoVotacaoEntity> startSession(
      @PathVariable @NotBlank UUID id,
      @RequestParam(required = false) Long minutes) {
    return new ResponseEntity<>(sessaoService.startSession(id, minutes), HttpStatus.OK);
  }

  @GetMapping("/result/{id}")
  public ResponseEntity<Map<String, Long>> result(@PathVariable @NotBlank UUID id) {
    return new ResponseEntity<>(sessaoService.calculateVotes(id), HttpStatus.OK);
  }
}

