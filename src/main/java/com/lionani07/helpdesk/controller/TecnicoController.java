package com.lionani07.helpdesk.controller;

import com.lionani07.helpdesk.domain.Tecnico;
import com.lionani07.helpdesk.domain.dto.TecnicoDto;
import com.lionani07.helpdesk.domain.request.TecnicoCreateRequest;
import com.lionani07.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable final Integer id) {
        final Tecnico tecnicoFound = this.tecnicoService.findById(id);
        return ResponseEntity.ok().body(tecnicoFound.toDto());
    }

    @GetMapping
    public ResponseEntity<Page<TecnicoDto>> findAll(@PageableDefault(page = 0, size = 2, sort = "nome", direction = Sort.Direction.DESC) Pageable pageable) {
        final var tecnicos = this.tecnicoService.findAll(pageable);
        return ResponseEntity.ok().body(tecnicos);
    }

    @PostMapping
    public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoCreateRequest request) {
        final var tecnicoSaved = this.tecnicoService.save(request);

        final var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(tecnicoSaved.getId()).toUri();

        return ResponseEntity.created(location).body(tecnicoSaved);
    }
}
