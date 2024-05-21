package com.eduardo.salaoDeBeleza.domain.usuario.dto;

import com.eduardo.salaoDeBeleza.domain.usuario.Authority;
import com.eduardo.salaoDeBeleza.domain.usuario.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String senha,
        Authority authority
) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getSenha(), usuario.getAuthority());
    }
}
