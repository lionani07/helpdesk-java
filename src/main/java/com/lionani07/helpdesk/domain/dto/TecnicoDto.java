package com.lionani07.helpdesk.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lionani07.helpdesk.domain.enums.Perfil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Builder(toBuilder = true)
@Getter
public class TecnicoDto {

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private Set<Perfil> perfis;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;
}
