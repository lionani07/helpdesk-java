package com.lionani07.helpdesk.domain.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class TecnicoCreateRequest {

    private String nome;
    private String cpf;
    private String email;
    private String senha;
}
