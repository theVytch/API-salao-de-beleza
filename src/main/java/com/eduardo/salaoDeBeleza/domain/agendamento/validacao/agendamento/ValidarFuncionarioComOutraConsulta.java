package com.eduardo.salaoDeBeleza.domain.agendamento.validacao.agendamento;

import com.eduardo.salaoDeBeleza.domain.agendamento.dto.DadosCadastroAgendamento;
import com.eduardo.salaoDeBeleza.infra.exception.ValidacaoException;
import com.eduardo.salaoDeBeleza.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarFuncionarioComOutraConsulta implements ValidadorAgendamentoSalao{

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Override
    public void validador(DadosCadastroAgendamento dados) {
        boolean funcionarioPossuiOutroAgendamentoNoMesmoHorario = agendamentoRepository.existsByFuncionarioIdAndData(dados.funcionarioId(), dados.data());
        if(funcionarioPossuiOutroAgendamentoNoMesmoHorario){
            throw new ValidacaoException("Funcionario já possui outra agendamento nesse mesmo horário");
        }
    }
}
