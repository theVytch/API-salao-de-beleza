package com.eduardo.salaoDeBeleza.domain.usuario.dto;

import com.eduardo.salaoDeBeleza.domain.usuario.Authority;
import com.eduardo.salaoDeBeleza.domain.usuario.Usuario;

public record DadosListagemUsuario(
        Long id,
        String login,
        Authority authority
) {
    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getLogin(), usuario.getAuthority());
    }
}
