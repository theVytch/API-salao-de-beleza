package com.eduardo.salaoDeBeleza.domain.funcionarios.dto;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Especialidade;
import com.eduardo.salaoDeBeleza.domain.funcionarios.Funcionario;
import com.eduardo.salaoDeBeleza.domain.trabalhos.Trabalho;
import com.eduardo.salaoDeBeleza.domain.trabalhos.dto.DadosListagemTrabalho;

import java.util.List;

public record DadosDetalhamentoFuncionarios(
        Long id,

        String nome,

        String telefone,

        String email,

        String cpf,

        List<Especialidade> especialidade,

        List<DadosListagemTrabalho> trabalho
) {
        public DadosDetalhamentoFuncionarios(Funcionario funcionario) {
                this(funcionario.getId(), funcionario.getNome(), funcionario.getTelefone(), funcionario.getEmail(), funcionario.getCpf(), funcionario.getEspecialidade(), funcionario.getTrabalho().stream().map(DadosListagemTrabalho::new).toList());
        }

}
