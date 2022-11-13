package com.lionani07.helpdesk.domain.request;

import lombok.Getter;

@Getter
public class TecnicoCreateRequest {

    private String nome;
    private String cpf;
    private String email;
    private String senha;
}
