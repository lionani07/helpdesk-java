package com.lionani07.helpdesk.controller;

import com.lionani07.helpdesk.domain.dto.ClienteDto;
import com.lionani07.helpdesk.domain.request.ClienteCreateRequest;
import com.lionani07.helpdesk.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClientController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody final ClienteCreateRequest request) {
        val clienteCrated = this.clienteService.save(request);
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteCrated.getId())
                .toUri();

        return ResponseEntity.created(location).body(clienteCrated);
    }
}
