package com.bolsovirtual.br.projector;

import com.bolsovirtual.br.domain.Pessoa;
import com.bolsovirtual.br.dto_request.PessoaDto;
import org.springframework.stereotype.Service;

@Service
public class PessoaDtoProjector {

    public PessoaDto project(Pessoa entity){
        return new PessoaDto().builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .sobrenome(entity.getSobrenome())
                .dataNascimento(entity.getDataNascimento())
                .dataExclusao(entity.getDataExclusao())
                .opcaoSexo(entity.getOpcaoSexo())
                .idOpcaoSexo(entity.getOpcaoSexo().getId())
                .build();
    }
}
