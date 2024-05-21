package com.eduardo.salaoDeBeleza.domain.trabalhos.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoTrabalho(
        @NotBlank
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        Double valor
) {
}
