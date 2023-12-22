package com.bolsovirtual.br.factory;

import com.bolsovirtual.br.domain.StatusVerificacaoUsuario;
import com.bolsovirtual.br.domain.VerificacaoUsuario;
import com.bolsovirtual.br.dto_request.VerificacaoUsuarioDto;
import com.bolsovirtual.br.enums.StatusVerificacaoUsuarioEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificacaoUsuarioFactory {

    public VerificacaoUsuario create(VerificacaoUsuarioDto dto){
        VerificacaoUsuario entidade = new VerificacaoUsuario().builder()
                .id(null)
                .codigoVerificacao(dto.getCodigoVerificacao())
                .dataHoraCodigo(dto.getDataHoraCodigo())
                .build();

        StatusVerificacaoUsuario status = new StatusVerificacaoUsuario();

        switch (StatusVerificacaoUsuarioEnum.fromInt(dto.getIdStatusVerificacaoUsuario())){
            case VERIFICANDO :
                status.setId(StatusVerificacaoUsuarioEnum.VERIFICANDO.getValor());
                break;
            case VERIFICADO:
                status.setId(StatusVerificacaoUsuarioEnum.VERIFICADO.getValor());
                break;
        }

        entidade.setStatusVerificacaoUsuario(status);

        return entidade;

    }

}
