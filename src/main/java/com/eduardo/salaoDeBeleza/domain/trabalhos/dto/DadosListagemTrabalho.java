package com.eduardo.salaoDeBeleza.domain.trabalhos.dto;

import com.eduardo.salaoDeBeleza.domain.trabalhos.Trabalho;

public record DadosListagemTrabalho(
        Long id,
        String nome,
        Double valor
) {
    public DadosListagemTrabalho(Trabalho trabalho){
        this(trabalho.getId(), trabalho.getNome(), trabalho.getValor());
    }
}
