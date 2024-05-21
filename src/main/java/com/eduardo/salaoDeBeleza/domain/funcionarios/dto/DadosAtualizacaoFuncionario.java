package com.eduardo.salaoDeBeleza.domain.funcionarios.dto;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Especialidade;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizacaoFuncionario(
        @NotNull
        Long id,

        String nome,

        String telefone,

        String email,

        String cpf,

        List<Especialidade> especialidade
) {
}
