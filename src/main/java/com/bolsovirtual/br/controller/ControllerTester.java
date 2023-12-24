package com.bolsovirtual.br.controller;

import com.bolsovirtual.br.request.UsuarioRequest;
import com.bolsovirtual.br.request.UsuarioVerificacaoRequest;
import com.bolsovirtual.br.service.UsuarioService;
import com.bolsovirtual.br.service.UsuarioVerificacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class ControllerTester {

    private final UsuarioService usuarioService;
    private final UsuarioVerificacaoService usuarioVerificacaoService;

    @PostMapping("/cadastro/usuario")
    public ResponseEntity<UsuarioRequest> cadastroUsuario(@RequestBody @Valid UsuarioRequest request) throws Exception {
        UsuarioRequest usuarioRequest = usuarioService.criarUsuario(request);
        return new ResponseEntity<>(usuarioRequest, HttpStatus.CREATED);
    }

    @PostMapping("/verificarCodigo")
    public ResponseEntity<Boolean> verificarCodigo(@RequestBody @Valid UsuarioVerificacaoRequest request) throws Exception {
        boolean valido = usuarioVerificacaoService.validarCodigoVerificacao(request);
        return new ResponseEntity<>(valido, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<UsuarioRequest> verificarCodigo(@PathVariable String id) throws Exception {
        UsuarioRequest usuarioRequest = usuarioService.buscarUsuarioPorId(UUID.fromString(id));
        return new ResponseEntity<>(usuarioRequest, HttpStatus.OK);
    }

}
