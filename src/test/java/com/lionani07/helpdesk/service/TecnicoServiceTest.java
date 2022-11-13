package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.domain.Tecnico;
import com.lionani07.helpdesk.repository.TecnicoRepository;
import com.lionani07.helpdesk.templates.TecnicoTemplates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Profile("test")
class TecnicoServiceTest {

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Test
    void createAllUsuarios() throws InterruptedException, ExecutionException {

        Tecnico liorge = TecnicoTemplates.builderDefault()
                .nome("B")
                .email("lio")
                .cpf("12")
                .build();
        Tecnico miriam = TecnicoTemplates.builderDefault().nome("A").build();
        Tecnico miriam1 = TecnicoTemplates.builderDefault().nome("A").build();

        this.tecnicoService.saveAll(List.of(miriam, miriam1, liorge));

        Assertions.assertEquals(2, tecnicoRepository.findAll().size());

    }

}