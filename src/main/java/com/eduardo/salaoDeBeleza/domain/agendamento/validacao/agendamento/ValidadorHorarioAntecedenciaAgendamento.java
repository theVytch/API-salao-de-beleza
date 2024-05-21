package com.eduardo.salaoDeBeleza.domain.agendamento.validacao.agendamento;

import com.eduardo.salaoDeBeleza.domain.agendamento.dto.DadosCadastroAgendamento;
import com.eduardo.salaoDeBeleza.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedenciaAgendamento implements ValidadorAgendamentoSalao{

    @Override
    public void validador(DadosCadastroAgendamento dados) {

        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        long diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Agendamento deve ser com antecedência mínima de 30 minutos");
        }
    }
}
