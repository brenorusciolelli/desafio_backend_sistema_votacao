package com.desafio.teste.desafiobackend.controllers;

import com.desafio.teste.desafiobackend.model.request.VoteRequest;
import com.desafio.teste.desafiobackend.service.VoteService;
import lombok.RequiredArgsConstructor;
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
  public void vote(@RequestBody VoteRequest request) {
    service.vote(
        request.sessionId(),
        request.cpf(),
        request.value()
    );
  }
}

