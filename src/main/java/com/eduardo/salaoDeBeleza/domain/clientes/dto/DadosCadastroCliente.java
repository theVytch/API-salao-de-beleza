package com.eduardo.salaoDeBeleza.domain.clientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroCliente(

        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf) {
}
