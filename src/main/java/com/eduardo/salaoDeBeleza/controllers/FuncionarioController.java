package com.eduardo.salaoDeBeleza.controllers;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Funcionario;
import com.eduardo.salaoDeBeleza.domain.funcionarios.dto.DadosAtualizacaoFuncionario;
import com.eduardo.salaoDeBeleza.domain.funcionarios.dto.DadosCadastroFuncionario;
import com.eduardo.salaoDeBeleza.domain.funcionarios.dto.DadosDetalhamentoFuncionarios;
import com.eduardo.salaoDeBeleza.domain.funcionarios.dto.DadosListagemFuncionario;
import com.eduardo.salaoDeBeleza.repositories.FuncionarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("funcionarios")
@SecurityRequirement(name = "bearer-key")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder){
        Funcionario funcionario = new Funcionario(dados);
        funcionarioRepository.save(funcionario);
        URI uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionarios(funcionario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemFuncionario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        Page page = funcionarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemFuncionario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable Long id){
        Funcionario funcionario = funcionarioRepository.findByIdAndAtivoTrue(id);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionarios(funcionario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFuncionario dados){
        Funcionario funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionarios(funcionario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Funcionario funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.inativar();
        return ResponseEntity.noContent().build();
    }
}
