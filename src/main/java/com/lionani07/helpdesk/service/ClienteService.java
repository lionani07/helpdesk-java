package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.domain.Cliente;
import com.lionani07.helpdesk.domain.dto.ClienteDto;
import com.lionani07.helpdesk.domain.request.ClienteCreateRequest;
import com.lionani07.helpdesk.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDto save(final ClienteCreateRequest request) {
        return this.clienteRepository.save(Cliente.of(request)).toDto();
    }
}
