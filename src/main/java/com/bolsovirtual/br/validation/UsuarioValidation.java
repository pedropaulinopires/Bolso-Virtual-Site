package com.bolsovirtual.br.validation;

import com.bolsovirtual.br.domain.Usuario;
import com.bolsovirtual.br.repository.IUsuarioRepository;
import com.bolsovirtual.br.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioValidation {

    private final IUsuarioRepository usuarioRepository;

    public void validarUsuarioParaCadastro(UsuarioRequest request) throws Exception {
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(request.getEmail());
        if(usuario != null)
            throw new Exception("Não é possível cadastrar usuário com esse email, pois ele já existe no sistema.");
    }
}
