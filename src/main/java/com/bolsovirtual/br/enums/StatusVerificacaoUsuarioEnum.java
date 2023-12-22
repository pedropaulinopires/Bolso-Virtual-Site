package com.bolsovirtual.br.enums;

public enum StatusVerificacaoUsuarioEnum {
    VERIFICANDO(-1),
    VERIFICADO(-2);

    private final int valor;

    StatusVerificacaoUsuarioEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static StatusVerificacaoUsuarioEnum fromInt(int valor) {
        for (StatusVerificacaoUsuarioEnum opcao : values()) {
            if (opcao.valor == valor) {
                return opcao;
            }
        }
        throw new IllegalArgumentException("Valor inválido para StatusVerificacaoUsuarioEnum: " + valor);
    }
}
