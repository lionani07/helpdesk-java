package com.lionani07.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Prioridade {
    BAIXA(0, "ABERTO"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    public static Prioridade toEnum(final Integer codigo) {
        return Arrays.stream(Prioridade.values())
                .filter(prioridade -> prioridade.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Prioridade inválida"));
    }
}
