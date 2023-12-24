package com.bolsovirtual.br.controller;

import com.bolsovirtual.br.request.UsuarioRequest;
import com.bolsovirtual.br.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequiredArgsConstructor
public class ControllerTester {

    private final UsuarioService usuarioService;

    //long minutesDifference = ChronoUnit.MINUTES.between(dateTime1, dateTime2);
    @PostMapping("/save")
    public ResponseEntity<UsuarioRequest> cadastroUsuario(@RequestBody @Valid UsuarioRequest request) throws Exception {
        UsuarioRequest usuarioRequest = usuarioService.criarUsuario(request);
        return new ResponseEntity<>(usuarioRequest, HttpStatus.CREATED);
    }

}
