package com.eduardo.salaoDeBeleza.controllers;

import com.eduardo.salaoDeBeleza.domain.agendamento.dto.*;
import com.eduardo.salaoDeBeleza.services.AgendamentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("agendamentos")
@SecurityRequirement(name = "bearer-key")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosCadastroAgendamento dados){
        DadosDetalhamentoAgendamento dto = agendamentoService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAgendamento>> listar(@PageableDefault(size = 10) Pageable paginacao){
        Page page = agendamentoService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/cancelados")
    public ResponseEntity<Page<DadosListagemAgendamentoCancelado>> listarCancelados(@PageableDefault(size = 10) Pageable paginacao){
        Page page = agendamentoService.listarCancelados(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var agendaDetalhada = agendamentoService.detalhar(id);
        return ResponseEntity.ok(agendaDetalhada);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody DadosCancelamentoAgendamento dados) {
        agendamentoService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
