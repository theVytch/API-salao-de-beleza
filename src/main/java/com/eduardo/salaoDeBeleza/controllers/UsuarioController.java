package com.eduardo.salaoDeBeleza.controllers;

import com.eduardo.salaoDeBeleza.domain.usuario.Usuario;
import com.eduardo.salaoDeBeleza.domain.usuario.dto.DadosAtualizacaoUsuario;
import com.eduardo.salaoDeBeleza.domain.usuario.dto.DadosCadastroUsuario;
import com.eduardo.salaoDeBeleza.domain.usuario.dto.DadosDetalhamentoUsuario;
import com.eduardo.salaoDeBeleza.repositories.UsuarioRepository;
import com.eduardo.salaoDeBeleza.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        DadosDetalhamentoUsuario dto = usuarioService.cadastrar(dados);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity listar(@PageableDefault(size = 10, sort = {"login"})Pageable paginacao){
        var page = usuarioService.listarUsuario(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoUsuario dados){
        Usuario usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.deleteById(usuario.getId());
        return ResponseEntity.noContent().build();
    }
}
