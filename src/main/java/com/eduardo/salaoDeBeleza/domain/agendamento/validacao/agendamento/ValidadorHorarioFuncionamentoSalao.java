package com.eduardo.salaoDeBeleza.domain.agendamento.validacao.agendamento;

import com.eduardo.salaoDeBeleza.domain.agendamento.dto.DadosCadastroAgendamento;
import com.eduardo.salaoDeBeleza.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoSalao implements ValidadorAgendamentoSalao{

    @Override
    public void validador(DadosCadastroAgendamento dados) {
        var dataConsulta = dados.data();

        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        boolean depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 20;

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Agendamento fora do horário de funcionamento do salão");
        }
    }
}
