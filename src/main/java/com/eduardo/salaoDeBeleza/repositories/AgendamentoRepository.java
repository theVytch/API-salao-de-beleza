package com.eduardo.salaoDeBeleza.repositories;

import com.eduardo.salaoDeBeleza.domain.agendamento.Agendamento;
import com.eduardo.salaoDeBeleza.domain.agendamento.dto.DadosListagemAgendamento;
import com.eduardo.salaoDeBeleza.domain.clientes.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {


    boolean existsByFuncionarioIdAndData(Long idFuncionario, LocalDateTime data);

    boolean existsByFuncionarioIdAndDataBetween(Long funcionarioId, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);


    Page<Agendamento> findAllByMotivoCancelamentoIsNull(Pageable paginacao);

    Page<Agendamento> findAllByMotivoCancelamentoIsNotNull(Pageable paginacao);
}
