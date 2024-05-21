package com.eduardo.salaoDeBeleza.services;

import com.eduardo.salaoDeBeleza.domain.agendamento.Agendamento;
import com.eduardo.salaoDeBeleza.domain.agendamento.dto.*;
import com.eduardo.salaoDeBeleza.domain.agendamento.validacao.agendamento.ValidadorAgendamentoSalao;
import com.eduardo.salaoDeBeleza.domain.agendamento.validacao.cancelamento.ValidadorCancelamentoDeAgendamento;
import com.eduardo.salaoDeBeleza.domain.clientes.Cliente;
import com.eduardo.salaoDeBeleza.domain.funcionarios.Funcionario;
import com.eduardo.salaoDeBeleza.infra.exception.ValidacaoException;
import com.eduardo.salaoDeBeleza.repositories.AgendamentoRepository;
import com.eduardo.salaoDeBeleza.repositories.ClienteRepository;
import com.eduardo.salaoDeBeleza.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private List<ValidadorAgendamentoSalao> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeAgendamento> cancelarAgendamento;

    public DadosDetalhamentoAgendamento agendar(DadosCadastroAgendamento dados){
        if(!funcionarioRepository.existsById(dados.funcionarioId())){
            throw new ValidacaoException("Id do funcionario informado não existe!");
        }

        if(!clienteRepository.existsById(dados.clienteId())){
            throw new ValidacaoException("Id do cliente informado não existe!");
        }

        validadores.forEach(v -> v.validador(dados));

        Funcionario funcionario = escolherFuncionario(dados);
        Cliente cliente = clienteRepository.getReferenceById(dados.clienteId());

        Agendamento agendamento = new Agendamento(null, funcionario, cliente, dados.data(), null);
        agendamentoRepository.save(agendamento);
        return new DadosDetalhamentoAgendamento(agendamento);
    }

    private Funcionario escolherFuncionario(DadosCadastroAgendamento dados) {
        if(dados.funcionarioId() != null){
            return funcionarioRepository.getReferenceById(dados.funcionarioId());
        }

        return funcionarioRepository.escolherFuncionarioAleatorioLivreAtivo(funcionarioRepository.getReferenceById(dados.funcionarioId()).getEspecialidade());

    }

    public void cancelar(DadosCancelamentoAgendamento dados) {
        if (!agendamentoRepository.existsById(dados.agendamentoId())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        cancelarAgendamento.forEach(v -> v.validar(dados));

        var consulta = agendamentoRepository.getReferenceById(dados.agendamentoId());
        consulta.cancelar(dados.motivoCancelamento());
    }


    public Page<DadosListagemAgendamento> listar(Pageable paginacao) {
        var agendamento = agendamentoRepository.findAllByMotivoCancelamentoIsNull(paginacao);
        return agendamento.map(DadosListagemAgendamento::new);
    }

    public Page<DadosListagemAgendamentoCancelado> listarCancelados(Pageable paginacao) {
        var agendamento = agendamentoRepository.findAllByMotivoCancelamentoIsNotNull(paginacao);
        return agendamento.map(DadosListagemAgendamentoCancelado::new);
    }

    public DadosDetalhamentoAgendamento detalhar(Long id) {
        var agendamento = agendamentoRepository.getReferenceById(id);
        return new DadosDetalhamentoAgendamento(agendamento);
    }
}
