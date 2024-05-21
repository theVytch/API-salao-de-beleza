package com.eduardo.salaoDeBeleza.domain.agendamento.dto;

import com.eduardo.salaoDeBeleza.domain.agendamento.MotivoCancelamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroAgendamento(
        @NotNull
        Long funcionarioId,

        @NotNull
        Long clienteId,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        MotivoCancelamento motivoCancelamento

) {
}
