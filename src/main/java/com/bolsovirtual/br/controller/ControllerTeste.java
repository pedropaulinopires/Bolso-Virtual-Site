package com.bolsovirtual.br.controller;

import com.bolsovirtual.br.dto_request.PessoaDto;
import com.bolsovirtual.br.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ControllerTeste {

    private final PessoaService pessoaService;

    @PostMapping("/salvar/pessoa")
    public ResponseEntity<PessoaDto> salvarPessoa(@RequestBody @Valid PessoaDto dto) throws Exception {
        PessoaDto pessoaDto = pessoaService.salvarPessoa(dto);
        return new ResponseEntity<>(pessoaDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PessoaDto> getPessoaById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(pessoaService.getPessoaById(id), HttpStatus.OK);
    }
}
