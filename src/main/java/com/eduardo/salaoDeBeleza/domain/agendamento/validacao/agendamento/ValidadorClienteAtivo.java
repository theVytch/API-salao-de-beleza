package com.eduardo.salaoDeBeleza.domain.agendamento.validacao.agendamento;

import com.eduardo.salaoDeBeleza.domain.agendamento.dto.DadosCadastroAgendamento;
import com.eduardo.salaoDeBeleza.infra.exception.ValidacaoException;
import com.eduardo.salaoDeBeleza.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteAtivo implements ValidadorAgendamentoSalao{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void validador(DadosCadastroAgendamento dados){
        boolean clienteEstaAtivo = clienteRepository.findAtivoById(dados.clienteId());
        if (!clienteEstaAtivo){
            throw new ValidacaoException("Não pode ser agendada com cliente excluído");
        }
    }
}
