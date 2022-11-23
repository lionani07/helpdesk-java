package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.repository.TecnicoRepository;
import com.lionani07.helpdesk.templates.TecnicoCreateRequestTemplates;
import com.lionani07.helpdesk.templates.TecnicoTemplates;
import com.lionani07.helpdesk.templates.TecnicoUpdateRequestTemplates;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Profile;

@SpringBootTest(properties = "spring.profiles.active=test")
@Profile("test")
class TecnicoServiceTest {

    @SpyBean
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

    @Test
    void updateTecnico() {

        final var request = TecnicoUpdateRequestTemplates
                .builderDefault()
                .build();

        final var tecnicoFound = TecnicoTemplates
                .builderDefault()
                .id(1)
                .build();

        Mockito.doReturn(tecnicoFound).when(this.tecnicoService).findById(1);

        this.tecnicoService.update(1, request);

        val tecnicoUpdated = this.tecnicoRepository.findById(1).orElse(null);
        Assertions.assertNotNull(tecnicoUpdated);
        Assertions.assertEquals(request.getCpf(), tecnicoUpdated.getCpf());
        Assertions.assertEquals(request.getNome(), tecnicoUpdated.getNome());
        Assertions.assertEquals(request.getEmail(), tecnicoUpdated.getEmail());
        Assertions.assertEquals(request.getSenha(), tecnicoUpdated.getSenha());

    }

}