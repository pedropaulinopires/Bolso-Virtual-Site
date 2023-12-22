package com.bolsovirtual.br.factory;

import com.bolsovirtual.br.domain.Usuario;
import com.bolsovirtual.br.dto_request.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioFactory {

    private final PessoaFactory pessoaFactory;

    private VerificacaoUsuarioFactory verificacaoUsuarioFactory;

    public Usuario create(UsuarioDto dto){
        Usuario entidade = new Usuario().builder()
                                        .id(null)
                                        .email(dto.getEmail())
                                        .senha(dto.getSenha())
                                        .pessoa(pessoaFactory.create(dto.getPessoaDto()))
                                        .verificacaoUsuario(verificacaoUsuarioFactory.create(dto.getVerificacaoUsuarioDto()))
                                        .dataExclusao(null)
                                        .build();
        return entidade;
    }
}
