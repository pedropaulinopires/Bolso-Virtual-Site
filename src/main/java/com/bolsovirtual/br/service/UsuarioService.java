package com.bolsovirtual.br.service;

import com.bolsovirtual.br.domain.Usuario;
import com.bolsovirtual.br.repository.IUsuarioRepository;
import com.bolsovirtual.br.request.UsuarioRequest;
import com.bolsovirtual.br.validation.UsuarioValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService{

    private final IUsuarioRepository usuarioRepository;
    private final UsuarioValidation usuarioValidation;
    private final EmailService emailService;

    /**
     * Método responsável para cadastrar o usuário
     * */
    public UsuarioRequest criarUsuario(UsuarioRequest request) throws Exception {
        usuarioValidation.validarUsuarioParaCadastro(request);
        Usuario usuarioCriado = usuarioRepository.save(request.buildCreate());
        request.buildProject(usuarioCriado);
        emailService.sendEmail(usuarioCriado);
        return request;
    }

    /**
     * Método responsável para verificar se o email existe
     * */
    public ResponseEntity<Boolean> emailValido(String email){
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(email);
        boolean emailValido = true;

        if(usuario != null)
            emailValido = false;

        return new ResponseEntity<>(emailValido, HttpStatus.OK);
    }
    

}
