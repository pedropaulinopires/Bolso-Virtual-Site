package com.bolsovirtual.br.domain;

import com.bolsovirtual.br.enums.OpcaoSexoEnum;
import com.bolsovirtual.br.enums.StatusVerificacaoUsuarioEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 200)
    private String sobrenome;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OpcaoSexoEnum sexo;

    /** Senha do usuário é criptografada */
    @Column(nullable = false, length = 500)
    private String senha;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private LocalDateTime dataExclusao;

    private int codigoVerificacao;

    private LocalDateTime dataCodigoVerificacao;

    private StatusVerificacaoUsuarioEnum statusVerificacao;



    public void gerarNovoCodigoVerificacao(){
        Random random = new Random();
        int codeNumber = random.nextInt(999999);

        this.codigoVerificacao = codeNumber;
        this.dataCodigoVerificacao = LocalDateTime.now();
    }
}
