package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.domain.Tecnico;
import com.lionani07.helpdesk.exceptions.ResourceAlreadyExistsException;
import com.lionani07.helpdesk.exceptions.ResourceNotFoundException;
import com.lionani07.helpdesk.repository.TecnicoRepository;
import com.lionani07.helpdesk.templates.TecnicoCreateRequestTemplates;
import com.lionani07.helpdesk.templates.TecnicoTemplates;
import com.lionani07.helpdesk.templates.TecnicoUpdateRequestTemplates;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@Profile("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TecnicoServiceTest {

    @InjectMocks
    private TecnicoService tecnicoService;

    @Mock
    private TecnicoRepository tecnicoRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindUserWhenAlreadyExists() {
        final var tecnicoFound = Optional.of(TecnicoTemplates
                .builderDefault()
                .id(1)
                .build());

        doReturn(tecnicoFound).when(this.tecnicoRepository).findById(1);

        assertDoesNotThrow(()-> this.tecnicoService.findById(1));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        doReturn(Optional.empty()).when(this.tecnicoRepository).findById(1);

        val exception = assertThrows(ResourceNotFoundException.class, () -> this.tecnicoService.findById(1));
        Assertions.assertEquals("Tecnico not found for id=1", exception.getMessage());
    }

    @Test
    void shouldBeCreateTecnico() {
        final var request = TecnicoCreateRequestTemplates
            .builderDefault()
            .build();

        Mockito.doReturn(Tecnico.of(request)).when(this.tecnicoRepository).save(any());

        this.tecnicoService.save(request);

        Mockito.verify(this.tecnicoRepository).save(any());

    }

    @Test
    void shouldNotCreateTecnicoWhenAlreadyExistsByEmail() {
        final var request = TecnicoCreateRequestTemplates
                .builderDefault()
                .build();

        Throwable throwable = new Throwable("", new Throwable(""));
        DataIntegrityViolationException exception = new DataIntegrityViolationException("", throwable);

        Mockito.doThrow(exception).when(this.tecnicoRepository).save(any());

        ResourceAlreadyExistsException resourceAlreadyExistsException = assertThrows(ResourceAlreadyExistsException.class, () -> this.tecnicoService.save(request));

        Assertions.assertEquals("Tecnico already exists by field=email, value=tecnico@gmail.com", resourceAlreadyExistsException.getMessage());

        Mockito.verify(this.tecnicoRepository).save(any());

    }

    @Test
    void shouldNotCreateTecnicoWhenAlreadyExistsByCpf() {
        final var request = TecnicoCreateRequestTemplates
                .builderDefault()
                .build();

        Throwable throwable = new Throwable("cpf", new Throwable("cpf"));
        DataIntegrityViolationException exception = new DataIntegrityViolationException("", throwable);

        Mockito.doThrow(exception).when(this.tecnicoRepository).save(any());

        ResourceAlreadyExistsException resourceAlreadyExistsException = assertThrows(ResourceAlreadyExistsException.class, () -> this.tecnicoService.save(request));

        Assertions.assertEquals("Tecnico already exists by field=cpf, value=50613433017", resourceAlreadyExistsException.getMessage());

        Mockito.verify(this.tecnicoRepository).save(any());

    }

    @Test
    void shouldUpdateTecnicoWhenAlreadyExists() {

        final var request = TecnicoUpdateRequestTemplates
                .builderDefault()
                .build();

        final var tecnico = TecnicoTemplates
                .builderDefault()
                .id(1)
                .build();

        doReturn(Optional.of(tecnico)).when(this.tecnicoRepository).findById(1);
        doReturn(tecnico).when(this.tecnicoRepository).save(any());

        assertDoesNotThrow(()-> this.tecnicoService.update(1, request));

    }

    @Test()
    void shouldNotUpdateTecnicoWhenAlreadyExistsOtherWithCpf() {

        final var request = TecnicoUpdateRequestTemplates
                .builderDefault()
                .build();

        final var tecnico = TecnicoTemplates
                .builderDefault()
                .id(1)
                .build();

        doReturn(Optional.of(tecnico)).when(this.tecnicoRepository).findById(1);

        Throwable throwable = new Throwable("cpf", new Throwable("cpf"));
        DataIntegrityViolationException exception = new DataIntegrityViolationException("", throwable);

        Mockito.doThrow(exception).when(this.tecnicoRepository).save(any());

        assertThrows(ResourceAlreadyExistsException.class, () -> this.tecnicoService.update(1, request));

    }

}