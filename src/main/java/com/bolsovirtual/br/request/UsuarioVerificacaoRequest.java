package com.bolsovirtual.br.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioVerificacaoRequest {

    @NotNull(message = "É necessário o Id do usuário para verificar o código")
    private UUID id;

    @NotNull(message = "É necessário informar o código de verificação")
    private int codigoVerificacao;
}
