package com.lionani07.helpdesk.service;

import com.lionani07.helpdesk.domain.Cliente;
import com.lionani07.helpdesk.domain.dto.ClienteDto;
import com.lionani07.helpdesk.domain.request.ClienteCreateRequest;
import com.lionani07.helpdesk.domain.request.ClienteUpdateRequest;
import com.lionani07.helpdesk.exceptions.ResourceNotFoundException;
import com.lionani07.helpdesk.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteDto save(final ClienteCreateRequest request) {
        return this.clienteRepository.save(Cliente.of(request)).toDto();
    }

    public ClienteDto update(final Integer id, final ClienteUpdateRequest request) {
        val clienteToUpdate = findById(id);
        BeanUtils.copyProperties(request, clienteToUpdate, "id");
        return this.clienteRepository.save(clienteToUpdate).toDto();
    }

    public Cliente findById(final Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Cliente.class, id));
    }
}
