package com.bolsovirtual.br.service;

import com.bolsovirtual.br.domain.Usuario;
import com.bolsovirtual.br.exception.BadRequestException;
import com.bolsovirtual.br.repository.IUsuarioRepository;
import com.bolsovirtual.br.request.UsuarioRequest;
import com.bolsovirtual.br.validation.UsuarioValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

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
    public boolean emailValido(String email){
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(email);
        boolean emailValido = true;

        if(usuario != null)
            emailValido = false;

        return emailValido;
    }

    /**
     * Métdo responsável por buscar usuário por id
     * */
    public UsuarioRequest buscarUsuarioPorId(UUID id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new BadRequestException("Usuário não encontrado com base no Id.") );

        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.buildProject(usuario);

        return  usuarioRequest;
    }

}
