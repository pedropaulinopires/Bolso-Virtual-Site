package com.bolsovirtual.br.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class VerificacaoUsuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 10)
    private String codigoVerificacao;

    private LocalDateTime dataHoraCodigo;

    @ManyToOne(fetch = FetchType.LAZY)
    private StatusVerificacaoUsuario statusVerificacaoUsuario;
}
