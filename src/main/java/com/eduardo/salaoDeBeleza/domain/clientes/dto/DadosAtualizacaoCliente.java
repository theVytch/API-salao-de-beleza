package com.eduardo.salaoDeBeleza.domain.clientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(

        @NotNull
        Long id,

        String nome,

        String telefone,

        String cpf
) {
}
