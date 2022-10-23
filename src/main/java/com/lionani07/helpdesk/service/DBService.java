package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.domain.Chamado;
import com.lionani07.helpdesk.domain.Cliente;
import com.lionani07.helpdesk.domain.Tecnico;
import com.lionani07.helpdesk.domain.enums.Perfil;
import com.lionani07.helpdesk.domain.enums.Prioridade;
import com.lionani07.helpdesk.domain.enums.Status;
import com.lionani07.helpdesk.repository.ChamadoRepository;
import com.lionani07.helpdesk.repository.ClienteRepository;
import com.lionani07.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        final Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "83961710090", "valdir@gmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        final Cliente cli1 = new Cliente(null, "Linus Torvalds", "54494387029", "torvals@gmail.com", "123");

        final Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
