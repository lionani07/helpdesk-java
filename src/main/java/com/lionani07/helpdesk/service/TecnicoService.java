package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.domain.Tecnico;
import com.lionani07.helpdesk.domain.dto.TecnicoDto;
import com.lionani07.helpdesk.exceptions.ResourceNotFoundException;
import com.lionani07.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(final Integer id) {
        return this.tecnicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Tecnico.class, id));
    }

    public List<TecnicoDto> findAll() {
        return this.tecnicoRepository.findAll()
                .stream()
                .map(Tecnico::toDto)
                .collect(Collectors.toList());
    }
}
