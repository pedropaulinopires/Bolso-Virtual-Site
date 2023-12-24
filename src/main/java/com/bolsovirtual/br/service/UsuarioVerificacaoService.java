package com.bolsovirtual.br.service;

import com.bolsovirtual.br.domain.Usuario;
import com.bolsovirtual.br.enums.StatusVerificacaoUsuarioEnum;
import com.bolsovirtual.br.repository.IUsuarioRepository;
import com.bolsovirtual.br.request.UsuarioRequest;
import com.bolsovirtual.br.request.UsuarioVerificacaoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioVerificacaoService {

    private final IUsuarioRepository usuarioRepository;

    /**
     * Método responsável por validar o código de verificação
     *
     * */
    public boolean validarCodigoVerificacao(UsuarioVerificacaoRequest request) throws Exception {
        Usuario usuario = usuarioRepository.findById(request.getId()).get();
        if(usuario == null)
            throw new Exception("Usuário não encontrado com base no Id.");

        long minutosDiferenca = ChronoUnit.MINUTES.between(usuario.getDataCodigoVerificacao(), LocalDateTime.now());

        if(usuario.getCodigoVerificacao() != request.getCodigoVerificacao()){
            throw new Exception("Código de verificação inválido, verifique seu email e tente novamente.");
        } else if(minutosDiferenca > 15) {
            throw new Exception("Código de verificação expirou.");
        } else {
            usuario.setStatusVerificacao(StatusVerificacaoUsuarioEnum.VERIFICADO);
            usuario.setCodigoVerificacao(000000);

            usuarioRepository.save(usuario);
            return true;
        }
    }

}
