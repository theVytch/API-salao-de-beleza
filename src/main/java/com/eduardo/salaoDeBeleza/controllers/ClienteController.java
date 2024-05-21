package com.eduardo.salaoDeBeleza.controllers;

import com.eduardo.salaoDeBeleza.domain.clientes.Cliente;
import com.eduardo.salaoDeBeleza.domain.clientes.dto.DadosAtualizacaoCliente;
import com.eduardo.salaoDeBeleza.domain.clientes.dto.DadosCadastroCliente;
import com.eduardo.salaoDeBeleza.domain.clientes.dto.DadosDetalhamentoCliente;
import com.eduardo.salaoDeBeleza.domain.clientes.dto.DadosListagemCliente;
import com.eduardo.salaoDeBeleza.repositories.ClienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;
import java.net.URI;

@RestController
@RequestMapping("clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder){
        Cliente cliente = new Cliente(dados);
        clienteRepository.save(cliente);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao){
        Page page = clienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhe(@PathVariable Long id){
        Cliente cliente = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados){
        Cliente cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.inativar();
        return ResponseEntity.noContent().build();
    }

}
