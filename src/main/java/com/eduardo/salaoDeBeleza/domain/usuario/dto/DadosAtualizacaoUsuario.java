package com.eduardo.salaoDeBeleza.domain.usuario.dto;

import com.eduardo.salaoDeBeleza.domain.usuario.Authority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(

        @NotNull
        Long id,

        String login,

        String senha,

        Authority authority
) {
}
