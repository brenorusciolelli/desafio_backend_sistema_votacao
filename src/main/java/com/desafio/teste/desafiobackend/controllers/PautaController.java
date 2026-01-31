package com.desafio.teste.desafiobackend.controllers;

import com.desafio.teste.desafiobackend.model.entity.PautaEntity;
import com.desafio.teste.desafiobackend.model.entity.SessaoVotacaoEntity;
import com.desafio.teste.desafiobackend.model.enums.VoteValue;
import com.desafio.teste.desafiobackend.model.repository.PautaRepository;
import com.desafio.teste.desafiobackend.model.repository.SessaoRepository;
import com.desafio.teste.desafiobackend.model.repository.VotoRepository;
import com.desafio.teste.desafiobackend.service.SessionService;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pautas")
@RequiredArgsConstructor
public class PautaController {

  private final PautaRepository  pautaRepository;
  private final SessionService   sessaoService;
  private final SessaoRepository sessaoRepository;
  private final VotoRepository   votoRepository;

  @PostMapping
  public PautaEntity create(@RequestBody PautaEntity pauta) {
    return pautaRepository.save(pauta);
  }

  @PostMapping("/{id}/session")
  public SessaoVotacaoEntity startSession(
      @PathVariable UUID id,
      @RequestParam(required = false) Long minutos) {

    return sessaoService.startSession(id, minutos);
  }

  @GetMapping("/{id}/result")
  public Map<String, Long> result(@PathVariable UUID id) {

    SessaoVotacaoEntity sessao = sessaoRepository.findByPautaId(id)
        .orElseThrow();

    return Map.of(
        "YES", votoRepository.countBySessionAndValue(sessao, VoteValue.YES),
        "NOT", votoRepository.countBySessionAndValue(sessao, VoteValue.NOT)
    );
  }
}

