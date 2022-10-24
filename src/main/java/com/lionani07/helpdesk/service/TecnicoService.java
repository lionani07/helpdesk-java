package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.domain.Tecnico;
import com.lionani07.helpdesk.exceptions.ResourceNotFoundException;
import com.lionani07.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(final Integer id) {
        return this.tecnicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Tecnico.class, id));
    }
}
