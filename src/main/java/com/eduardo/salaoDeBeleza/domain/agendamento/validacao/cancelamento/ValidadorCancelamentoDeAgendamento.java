package com.eduardo.salaoDeBeleza.domain.agendamento.validacao.cancelamento;

import com.eduardo.salaoDeBeleza.domain.agendamento.dto.DadosCancelamentoAgendamento;

public interface ValidadorCancelamentoDeAgendamento {

    void validar(DadosCancelamentoAgendamento dados);
}
