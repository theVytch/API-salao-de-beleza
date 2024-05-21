package com.eduardo.salaoDeBeleza.domain.trabalhos.dto;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Funcionario;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTrabalho(
        @NotBlank
        String nome,

        @NotBlank
        Double valor
) {
}
