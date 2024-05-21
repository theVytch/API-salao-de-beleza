package com.eduardo.salaoDeBeleza.domain.agendamento.dto;

import com.eduardo.salaoDeBeleza.domain.agendamento.Agendamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosListagemAgendamento(
        Long id,
        String funcionario,
        String cliente,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data
) {
        public DadosListagemAgendamento(Agendamento agendamento){
                this(agendamento.getId(), agendamento.getFuncionario().getNome(), agendamento.getCliente().getNome(), agendamento.getData());
        }
}
