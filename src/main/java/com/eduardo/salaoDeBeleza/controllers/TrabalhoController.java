package com.eduardo.salaoDeBeleza.controllers;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Funcionario;
import com.eduardo.salaoDeBeleza.domain.trabalhos.Trabalho;
import com.eduardo.salaoDeBeleza.domain.trabalhos.dto.DadosAtualizacaoTrabalho;
import com.eduardo.salaoDeBeleza.domain.trabalhos.dto.DadosCadastroTrabalho;
import com.eduardo.salaoDeBeleza.domain.trabalhos.dto.DadosDetalhamentoTrabalhos;
import com.eduardo.salaoDeBeleza.domain.trabalhos.dto.DadosListagemTrabalho;
import com.eduardo.salaoDeBeleza.repositories.FuncionarioRepository;
import com.eduardo.salaoDeBeleza.repositories.TrabalhoRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("trabalhos")
@SecurityRequirement(name = "bearer-key")
public class TrabalhoController {

    @Autowired
    private TrabalhoRepository trabalhoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/{idFuncionario}")
    @Transactional
    public ResponseEntity cadastrar(@PathVariable Long idFuncionario, @RequestBody @Valid DadosCadastroTrabalho dados,
                                    UriComponentsBuilder uriBuilder){
        Optional<Funcionario> funcionario = funcionarioRepository.findFuncionarioById(idFuncionario);
        Trabalho trabalho = new Trabalho(dados, funcionario.get());

        trabalhoRepository.save(trabalho);

        URI uri = uriBuilder.path("/trabalhos/{id}").buildAndExpand(trabalho.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTrabalhos(trabalho));
    }

    @GetMapping("/listar/{idFuncionario}")
    public ResponseEntity<Page<DadosListagemTrabalho>> listar(@PathVariable Long idFuncionario, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        Page page = trabalhoRepository.findAllByFuncionarioId(idFuncionario, paginacao).map(DadosListagemTrabalho::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{idTrabalho}")
    public ResponseEntity detalhe(@PathVariable Long idTrabalho){
        Trabalho trabalho = trabalhoRepository.getReferenceById(idTrabalho);
        return ResponseEntity.ok(new DadosDetalhamentoTrabalhos(trabalho));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoTrabalho dados){
        Trabalho trabalho = trabalhoRepository.getReferenceById(dados.id());
        trabalho.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTrabalhos(trabalho));
    }

    @DeleteMapping("/{idTrabalho}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long idTrabalho){
        trabalhoRepository.deleteById(idTrabalho);
        return ResponseEntity.noContent().build();
    }
}
