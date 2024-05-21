package com.eduardo.salaoDeBeleza.domain.funcionarios.dto;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record DadosCadastroFuncionario(

        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        @NotBlank
        String email,

        @NotBlank
        String cpf,

        @NotNull
        @Size(min = 1)
        List<Especialidade> especialidade
) {
}
