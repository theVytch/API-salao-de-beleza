package com.eduardo.salaoDeBeleza.domain.trabalhos.dto;

import com.eduardo.salaoDeBeleza.domain.trabalhos.Trabalho;

public record DadosDetalhamentoTrabalhos(Long id, String nome, Double valor) {

    public DadosDetalhamentoTrabalhos(Trabalho trabalho){
        this(trabalho.getId(), trabalho.getNome(), trabalho.getValor());
    }
}
