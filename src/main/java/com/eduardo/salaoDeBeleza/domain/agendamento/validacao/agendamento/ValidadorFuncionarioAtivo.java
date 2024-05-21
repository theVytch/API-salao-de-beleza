package com.eduardo.salaoDeBeleza.domain.agendamento.validacao.agendamento;

import com.eduardo.salaoDeBeleza.domain.agendamento.dto.DadosCadastroAgendamento;
import com.eduardo.salaoDeBeleza.infra.exception.ValidacaoException;
import com.eduardo.salaoDeBeleza.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorFuncionarioAtivo implements ValidadorAgendamentoSalao{

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public void validador(DadosCadastroAgendamento dados){
        if(dados.funcionarioId() == null){
            return;
        }

        boolean funcionarioEstaAtivo = funcionarioRepository.findAtivoById(dados.funcionarioId());
        if (!funcionarioEstaAtivo){
            throw new ValidacaoException("Não pode ser agendada com funcionario excluído");
        }
    }
}
