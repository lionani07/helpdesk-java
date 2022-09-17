package com.lionani07.helpdesk.domain;

import com.lionani07.helpdesk.domain.enums.Perfil;
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

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

}