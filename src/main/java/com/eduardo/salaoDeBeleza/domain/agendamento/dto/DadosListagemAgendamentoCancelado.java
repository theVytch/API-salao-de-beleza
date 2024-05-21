package com.eduardo.salaoDeBeleza.domain.agendamento.dto;

import com.eduardo.salaoDeBeleza.domain.agendamento.Agendamento;
import com.eduardo.salaoDeBeleza.domain.agendamento.MotivoCancelamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosListagemAgendamentoCancelado(
        Long id,
        String funcionario,
        String cliente,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        MotivoCancelamento motivoCancelamento
) {
        public DadosListagemAgendamentoCancelado(Agendamento agendamento){
                this(agendamento.getId(), agendamento.getFuncionario().getNome(), agendamento.getCliente().getNome(), agendamento.getData(), agendamento.getMotivoCancelamento());
        }
}
