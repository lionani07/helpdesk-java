package com.lionani07.helpdesk.controller;

import com.lionani07.helpdesk.domain.Tecnico;
import com.lionani07.helpdesk.domain.dto.TecnicoDto;
import com.lionani07.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
