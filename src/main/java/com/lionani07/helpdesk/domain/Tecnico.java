package com.lionani07.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lionani07.helpdesk.domain.dto.TecnicoDto;
import com.lionani07.helpdesk.domain.enums.Perfil;
import com.lionani07.helpdesk.domain.request.TecnicoCreateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Tecnico extends Pessoa {

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        addPerfil(Perfil.TECNICO);
    }

    @Builder
    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
    }

    public static Tecnico of(TecnicoCreateRequest request) {
        return new Tecnico(null, request.getNome(), request.getCpf(), request.getEmail(), request.getSenha());
    }

    public TecnicoDto toDto() {
        return TecnicoDto.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .perfis(this.perfis)
                .dataCriacao(this.dataCriacao)
                .build();
    }
}
