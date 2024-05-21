package com.eduardo.salaoDeBeleza.domain.funcionarios.dto;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Funcionario;

public record DadosListagemFuncionario(
        Long id,
        String nome,
        String telefone,
        String email
) {

    public DadosListagemFuncionario(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getTelefone(), funcionario.getEmail());
    }
}
