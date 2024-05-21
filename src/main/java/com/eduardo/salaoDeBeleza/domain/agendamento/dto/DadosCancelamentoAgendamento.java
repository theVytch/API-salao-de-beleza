package com.eduardo.salaoDeBeleza.domain.agendamento.dto;

import com.eduardo.salaoDeBeleza.domain.agendamento.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoAgendamento(

        @NotNull
        Long agendamentoId,

        @NotNull
        MotivoCancelamento motivoCancelamento
) {
}
