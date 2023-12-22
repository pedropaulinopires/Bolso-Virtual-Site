package com.bolsovirtual.br.factory;

import com.bolsovirtual.br.domain.OpcaoSexo;
import com.bolsovirtual.br.domain.Pessoa;
import com.bolsovirtual.br.dto_request.PessoaDto;
import com.bolsovirtual.br.enums.OpcaoSexoEnum;
import org.springframework.stereotype.Service;

@Service
public class PessoaFactory {

    public Pessoa create(PessoaDto dto){
        Pessoa entity = new Pessoa().builder()
                .id(null)
                .nome(dto.getNome())
                .sobrenome(dto.getSobrenome())
                .dataNascimento(dto.getDataNascimento())
                .dataExclusao(null)
                .build();

        OpcaoSexo sexo = new OpcaoSexo();

        /** Sexos */
        switch (OpcaoSexoEnum.fromInt(dto.getIdOpcaoSexo())){
            case MASCULINO:
                sexo.setId(OpcaoSexoEnum.MASCULINO.getValor());
                break;
            case FEMININO:
                sexo.setId(OpcaoSexoEnum.FEMININO.getValor());
                break;
            case SEM_INFORMAR:
                sexo.setId(OpcaoSexoEnum.SEM_INFORMAR.getValor());
                break;
        }

        entity.setOpcaoSexo(sexo);

        return entity;
    }
}
