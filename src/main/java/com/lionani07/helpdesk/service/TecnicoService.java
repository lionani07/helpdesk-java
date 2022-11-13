package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.domain.Tecnico;
import com.lionani07.helpdesk.domain.dto.TecnicoDto;
import com.lionani07.helpdesk.domain.request.TecnicoCreateRequest;
import com.lionani07.helpdesk.exceptions.ResourceNotFoundException;
import com.lionani07.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(final Integer id) {
        return this.tecnicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Tecnico.class, id));
    }

    public Page<TecnicoDto> findAll(Pageable pageable) {
        return this.tecnicoRepository.findAll(pageable)
                .map(Tecnico::toDto);
    }

    public TecnicoDto save(final @Valid TecnicoCreateRequest request) {
        return this.tecnicoRepository.save(Tecnico.of(request)).toDto();
    }
}
