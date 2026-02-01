package com.desafio.teste.desafiobackend.controllers;

import com.desafio.teste.desafiobackend.model.entity.VoteValueEntity;
import com.desafio.teste.desafiobackend.model.request.VoteRequest;
import com.desafio.teste.desafiobackend.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VotoController {

  private final VoteService service;

  @PostMapping
  public ResponseEntity<VoteValueEntity> vote(@RequestBody @Valid VoteRequest request) {
    return new ResponseEntity<>(service.vote(request.sessionId(), request.cpf(), request.value()), HttpStatus.CREATED);
  }
}

