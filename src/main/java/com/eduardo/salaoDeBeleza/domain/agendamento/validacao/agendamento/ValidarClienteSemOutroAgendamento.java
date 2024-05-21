package com.eduardo.salaoDeBeleza.domain.agendamento.validacao.agendamento;

import com.eduardo.salaoDeBeleza.domain.agendamento.dto.DadosCadastroAgendamento;
import com.eduardo.salaoDeBeleza.infra.exception.ValidacaoException;
import com.eduardo.salaoDeBeleza.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarClienteSemOutroAgendamento implements ValidadorAgendamentoSalao{

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Override
    public void validador(DadosCadastroAgendamento dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(20);
        boolean funcionarioPossuiOutroAgendamentoNoMesmoHorario = agendamentoRepository.existsByFuncionarioIdAndDataBetween(dados.funcionarioId(), primeiroHorario, ultimoHorario);

        if(funcionarioPossuiOutroAgendamentoNoMesmoHorario){
            throw new ValidacaoException("Cliente já possui outro agendamento nesse mesmo horário");
        }
    }
}
