package com.lionani07.helpdesk.repository;

import com.lionani07.helpdesk.domain.Pessoa;
import com.lionani07.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
