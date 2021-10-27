package com.lionani07.helpdesk.domain.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PerfilTest {

    @Test
    void shouldReturnADMIN() {
        Assertions.assertEquals(Perfil.ADMIN, Perfil.toEnum(0));
    }

    @Test
    void shouldReturnCLIENTE() {
        Assertions.assertEquals(Perfil.CLIENTE, Perfil.toEnum(1));
    }

    @Test
    void shouldReturnTECNICO() {
        Assertions.assertEquals(Perfil.TECNICO, Perfil.toEnum(2));
    }

    @Test
    void shouldThrowIlegalException() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> Perfil.toEnum(4));
    }

}