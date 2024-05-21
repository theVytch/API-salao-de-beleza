package com.eduardo.salaoDeBeleza.repositories;

import com.eduardo.salaoDeBeleza.domain.trabalhos.Trabalho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {

    @Query("""
            select t from Trabalho t
            where t.funcionario.id = :funcionarioId
            and t.funcionario.ativo = true
            """)
    Page<Trabalho> findAllByFuncionarioId(@Param("funcionarioId") Long funcionarioId, Pageable paginacao);
}
