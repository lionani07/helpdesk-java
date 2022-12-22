package com.lionani07.helpdesk.domain;

import com.lionani07.helpdesk.domain.dto.ClienteDto;
import com.lionani07.helpdesk.domain.enums.Perfil;
import com.lionani07.helpdesk.domain.request.ClienteCreateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        addPerfil(Perfil.CLIENTE);
    }

    @Builder
    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public static Cliente of(final ClienteCreateRequest request) {
        return new Cliente(null, request.getNome(), request.getCpf(), request.getEmail(), request.getSenha());
    }

    public ClienteDto toDto() {
        return ClienteDto.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .dataCriacao(this.dataCriacao)
                .perfis(this.perfis)
                .build();
    }
}
