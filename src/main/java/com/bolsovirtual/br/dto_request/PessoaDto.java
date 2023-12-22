package com.bolsovirtual.br.dto_request;

import com.bolsovirtual.br.domain.OpcaoSexo;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PessoaDto {
    private Long id;
    @NotBlank(message = "É necessário informar o nome.")
    @Size(min = 3, message = "O nome precisa ter no minimo 3 caracteres.")
    private String nome;

    @NotBlank(message = "É necessário informar o sobrenome.")
    @Size(min = 3, message = "O sobrenome precisa ter no minimo 3 caracteres.")
    private String sobrenome;

    @NotNull(message = "É necessário informar data de nascimento.")
    private LocalDate dataNascimento;
    private LocalDateTime dataExclusao;

    @Min(value = -3,message = "É necessário informar o sexo.")
    @Max(value = -1, message = "Sexo é obrigatório.")
    private int idOpcaoSexo;
    private OpcaoSexo opcaoSexo;
}
