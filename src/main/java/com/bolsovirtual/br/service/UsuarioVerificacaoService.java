package com.bolsovirtual.br.service;

import com.bolsovirtual.br.domain.Usuario;
import com.bolsovirtual.br.enums.StatusVerificacaoUsuarioEnum;
import com.bolsovirtual.br.exception.BadRequestException;
import com.bolsovirtual.br.repository.IUsuarioRepository;
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
    private final EmailService emailService;

    /**
     * Método responsável por validar o código de verificação
     *
     * */
    public boolean validarCodigoVerificacao(UsuarioVerificacaoRequest request) throws Exception {
        Usuario usuario = usuarioRepository.findById(request.getId()).orElseThrow(() -> new BadRequestException("Usuário não encontrado com base no Id.") );
        long minutosDiferenca = ChronoUnit.MINUTES.between(usuario.getDataCodigoVerificacao(), LocalDateTime.now());

        if(usuario.getStatusVerificacao() == StatusVerificacaoUsuarioEnum.VERIFICADO)
            throw new Exception("Usuário já verificado.");

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

    /**
     * Método responsável por gerar novo código de verificação
     *
     * */
    public void gerarNovoCodigoVerificacaoUsuario(UUID id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new BadRequestException("Usuário não encontrado com base no Id.") );
        if(usuario.getStatusVerificacao() == StatusVerificacaoUsuarioEnum.VERIFICADO)
            throw new Exception("Usuário já verificado.");

        usuario.gerarNovoCodigoVerificacao();
        usuarioRepository.save(usuario);
        emailService.sendEmail(usuario);
    }

}
