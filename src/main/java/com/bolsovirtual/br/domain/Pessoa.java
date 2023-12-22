package com.bolsovirtual.br.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Pessoa {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private LocalDateTime dataExclusao;

    /** Relacionamento de n : 1 com sexo*/
    @ManyToOne(fetch = FetchType.LAZY)
    private OpcaoSexo opcaoSexo;
}
