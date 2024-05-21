package com.eduardo.salaoDeBeleza.repositories;

import com.eduardo.salaoDeBeleza.domain.clientes.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select c.ativo from Cliente c
            where c.id = :clienteId
            """)
    boolean findAtivoById(Long clienteId);
}
