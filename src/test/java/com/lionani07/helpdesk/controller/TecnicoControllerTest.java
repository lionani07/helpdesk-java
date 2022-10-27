package com.lionani07.helpdesk.controller;

import com.lionani07.helpdesk.domain.Tecnico;
import com.lionani07.helpdesk.exceptions.ResourceNotFoundException;
import com.lionani07.helpdesk.service.TecnicoService;
import com.lionani07.helpdesk.templates.TecnicoTemplates;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TecnicoControllerTest {

    private static final String URL_TECNICOS = "/tecnicos";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TecnicoService  tecnicoService;

    @Test
    void shouldFindTecnico() throws Exception {

        final var tecnico = TecnicoTemplates.builderDefault().build();

        doReturn(tecnico).when(this.tecnicoService).findById(tecnico.getId());

        this.mockMvc.perform(get(URL_TECNICOS.concat("/{id}"), tecnico.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", Matchers.is(tecnico.getId())))
                .andExpect(jsonPath("cpf", Matchers.is(tecnico.getCpf())))
                .andExpect(jsonPath("nome", Matchers.is(tecnico.getNome())));

    }

    @Test
    void shouldReturnNotFound() throws Exception {

        final var tecnicoId = 1;

        doThrow(new ResourceNotFoundException(Tecnico.class, tecnicoId)).when(this.tecnicoService).findById(tecnicoId);

        this.mockMvc.perform(get(URL_TECNICOS.concat("/{id}"), tecnicoId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error", Matchers.is("Resource not found")))
                .andExpect(jsonPath("message", Matchers.is("Tecnico not found for id=1")));

    }

}