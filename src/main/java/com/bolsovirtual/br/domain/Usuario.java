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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    /** Senha do usuário é criptografada */
    @Column(nullable = false, length = 500)
    private String senha;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private VerificacaoUsuario verificacaoUsuario;

    private LocalDateTime dataExclusao;
}
