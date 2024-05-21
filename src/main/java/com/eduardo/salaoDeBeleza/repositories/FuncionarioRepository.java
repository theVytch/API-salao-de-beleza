package com.eduardo.salaoDeBeleza.repositories;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Funcionario;
import com.eduardo.salaoDeBeleza.domain.funcionarios.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Page<Funcionario> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select f.ativo from Funcionario f
            where f.id = :funcionarioId
            """)
    boolean findAtivoById(Long funcionarioId);

    @Query("""
            SELECT DISTINCT f
            FROM Funcionario f
            JOIN f.especialidade fe
            WHERE 
            fe in :especialidades
            AND
            f.ativo = true
            order by rand()
            limit 1
            """)
    Funcionario escolherFuncionarioAleatorioLivreAtivo(@Param("especialidades") List<Especialidade> especialidades);

    @Query("""
            SELECT f FROM Funcionario f
            WHERE f.id = :id AND f.ativo = true
            """)
    Optional<Funcionario> findFuncionarioById(@Param("id") Long id);

    Funcionario findByIdAndAtivoTrue(Long id);
}
