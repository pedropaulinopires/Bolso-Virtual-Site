package com.bolsovirtual.br.dto_request;

import com.bolsovirtual.br.domain.Pessoa;
import com.bolsovirtual.br.domain.VerificacaoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private Long id;

    @Email(message = "Email inválido.")
    @NotBlank(message = "É necessário informar um email")
    private String email;

    @NotBlank(message = "É obrigatório informar a senha.")
    @Min(value = 8, message = "A senha precisa ter no mínimo 8 caracteres.")
    private String senha;

    private PessoaDto pessoaDto;
    private Long idPessoa;

    private VerificacaoUsuarioDto verificacaoUsuarioDto;
    private Long idVerificacaoUsuario;

    private LocalDateTime DataExclusao;
}
