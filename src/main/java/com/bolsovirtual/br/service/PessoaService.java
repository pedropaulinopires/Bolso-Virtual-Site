package com.bolsovirtual.br.service;

import com.bolsovirtual.br.domain.Pessoa;
import com.bolsovirtual.br.dto_request.PessoaDto;
import com.bolsovirtual.br.factory.PessoaFactory;
import com.bolsovirtual.br.projector.PessoaDtoProjector;
import com.bolsovirtual.br.repository.IPessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final IPessoaRepository pessoaRepository;
    private final PessoaFactory pessoaFactory;
    private final PessoaDtoProjector pessoaProjector;
    public PessoaDto salvarPessoa(PessoaDto dto) throws Exception {
        Pessoa pessoa = pessoaFactory.create(dto);
        Pessoa pessoaEntidade = pessoaRepository.save(pessoa);
        PessoaDto pessoaDto = pessoaProjector.project(pessoaEntidade);
        return pessoaDto;
    }

    public PessoaDto getPessoaById(Long id){
        PessoaDto dto = pessoaProjector.project(pessoaRepository.findById(id).get());
        return dto;
    }

}
