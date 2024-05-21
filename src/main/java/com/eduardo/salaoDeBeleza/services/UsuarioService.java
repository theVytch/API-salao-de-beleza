package com.eduardo.salaoDeBeleza.services;

import com.eduardo.salaoDeBeleza.domain.usuario.Usuario;
import com.eduardo.salaoDeBeleza.domain.usuario.dto.DadosCadastroUsuario;
import com.eduardo.salaoDeBeleza.domain.usuario.dto.DadosDetalhamentoUsuario;
import com.eduardo.salaoDeBeleza.domain.usuario.dto.DadosListagemUsuario;
import com.eduardo.salaoDeBeleza.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuario dados) {
        var senha = new BCryptPasswordEncoder().encode(dados.senha());
        var usuario = new Usuario(null, dados.login(), senha, dados.authority());

        usuarioRepository.save(usuario);

        return new DadosDetalhamentoUsuario(usuario);
    }

    public Page<DadosListagemUsuario> listarUsuario(Pageable paginacao){
        return usuarioRepository.findAll(paginacao).map(DadosListagemUsuario::new);
    }
}
