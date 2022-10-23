package com.lionani07.helpdesk.repository;

import com.lionani07.helpdesk.domain.Cliente;
import com.lionani07.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
