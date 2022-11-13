package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.repository.TecnicoRepository;
import com.lionani07.helpdesk.templates.TecnicoCreateRequestTemplates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest(properties = "spring.profiles.active=test")
@Profile("test")
class TecnicoServiceTest {

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Test
    void createTecnico() {

        final var request = TecnicoCreateRequestTemplates
                .builderDefault()
                .build();


        this.tecnicoService.save(request);

        Assertions.assertEquals(1, tecnicoRepository.findAll().size());

    }

}