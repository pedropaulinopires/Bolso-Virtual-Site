package com.bolsovirtual.br.enums;

public enum OpcaoSexoEnum {
    MASCULINO(-1),
    FEMININO(-2),
    SEM_INFORMAR(-3);

    private final int valor;

    OpcaoSexoEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static OpcaoSexoEnum fromInt(int valor) {
        for (OpcaoSexoEnum opcao : values()) {
            if (opcao.valor == valor) {
                return opcao;
            }
        }
        throw new IllegalArgumentException("Valor inválido para OpcaoSexoEnum: " + valor);
    }
}
