package com.bolsovirtual.br.dto_request;

import com.bolsovirtual.br.domain.StatusVerificacaoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VerificacaoUsuarioDto {


    private Long id;

    @Column(unique = true, length = 10)
    @NotBlank(message = "O código de verificação é obrigatório.")
    private String codigoVerificacao;

    private LocalDateTime dataHoraCodigo;

    @NotBlank(message = "O status da verificação é obrigatório.")
    private StatusVerificacaoUsuarioDto statusVerificacaoUsuarioDto;

    @NotBlank(message = "O idStatus da verificação é obrigatório.")
    private int idStatusVerificacaoUsuario;
}
