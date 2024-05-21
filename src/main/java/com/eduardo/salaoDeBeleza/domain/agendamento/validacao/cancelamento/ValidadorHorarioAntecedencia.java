package com.eduardo.salaoDeBeleza.domain.agendamento.validacao.cancelamento;

import com.eduardo.salaoDeBeleza.domain.agendamento.dto.DadosCancelamentoAgendamento;
import com.eduardo.salaoDeBeleza.infra.exception.ValidacaoException;
import com.eduardo.salaoDeBeleza.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeAgendamento{

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Override
    public void validar(DadosCancelamentoAgendamento dados) {

        var consulta = agendamentoRepository.getReferenceById(dados.agendamentoId());
        var agora = LocalDateTime.now();
        var diferencaEmHora = Duration.between(agora, consulta.getData()).toHours();

        if(diferencaEmHora < 2){
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 2h!");
        }

    }
}
