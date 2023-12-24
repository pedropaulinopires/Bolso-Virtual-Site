package com.bolsovirtual.br.request;

import com.bolsovirtual.br.domain.Usuario;
import com.bolsovirtual.br.enums.OpcaoSexoEnum;
import com.bolsovirtual.br.enums.StatusVerificacaoUsuarioEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioRequest {

    private UUID id;

    @NotBlank(message = "É necessário informar o nome")
    @Size(min = 3, message = "O nome precisa ter no mínimo 3 caracteres")
    private  String nome;

    @NotBlank(message = "É necessário informar o sobrenome")
    @Size(min = 3, message = "O sobrenome precisa ter no mínimo 3 caracteres")
    private String sobrenome;

    @NotBlank(message = "É necessário informar a email")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "É necessário informar a senha")
    @Size(min = 8, message = "A senha precisa ter no mínimo 8 caracteres")
    private String senha;

    @NotNull(message = "É necessário informar o sexo")
    private OpcaoSexoEnum sexo;

    @NotNull(message = "É necessário informar uma data de nascimento")
    @Past(message = "A data de nascimento deve ser anterior à data atual")
    private LocalDate dataNascimento;

    private LocalDateTime dataExclusao;

    private int codigoVerificacao;

    private LocalDateTime dataCodigoVerificacao;

    private StatusVerificacaoUsuarioEnum statusVerificacao;


    public Usuario buildCreate(){
        return new Usuario().builder()
                .id(null) // id nulo, pois estou criando usuário
                .nome(this.nome)
                .sobrenome(this.sobrenome)
                .email(this.email)
                .senha(getPasswordEncoder().encode(this.senha)) // criptografo a senha do usuário
                .sexo(this.sexo)
                .dataNascimento(this.dataNascimento)
                .dataExclusao(null) // id nulo, pois estou criando usuário
                .codigoVerificacao(gerarCodigoVerificao()) // gerando código, pois estou criando usuário
                .dataCodigoVerificacao(LocalDateTime.now()) // data atual ,pois estou criando o usuário
                .statusVerificacao(StatusVerificacaoUsuarioEnum.VERIFICANDO) //verificando, pois estou criando o usuário
                .build();
    }

    public void buildProject(Usuario entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.sobrenome = entity.getSobrenome();
        this.email = entity.getEmail();
        this.senha = null; // não exibo a senha por motivo de segurança do usuário
        this.sexo = entity.getSexo();
        this.dataNascimento = entity.getDataNascimento();
        this.dataExclusao = entity.getDataExclusao();
        this.codigoVerificacao = 000000; // não exibo por motivo de segurança
        this.dataCodigoVerificacao = null;  // não exibo por motivo de segurança
        this.statusVerificacao = entity.getStatusVerificacao();
    }

    private int gerarCodigoVerificao(){
        Random random = new Random();
        int codeNumber = random.nextInt(999999);

        return  codeNumber;
    }

    private BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
