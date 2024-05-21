package com.eduardo.salaoDeBeleza.domain.usuario.dto;

import com.eduardo.salaoDeBeleza.domain.usuario.Authority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCadastroUsuario(
        @NotBlank
        String login,

        @NotBlank
        @Size(min = 5)
        String senha,

        @NotNull
        Authority authority
) {
}
