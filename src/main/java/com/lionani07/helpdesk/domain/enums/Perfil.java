package com.lionani07.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    private Integer codigo;
    private String descricao;

    public static Perfil toEnum(final Integer codigo) {
        return Arrays.stream(Perfil.values())
                .filter(perfil -> perfil.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Perfil inválido"));
    }
}
