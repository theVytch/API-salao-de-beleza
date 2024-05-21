package com.eduardo.salaoDeBeleza.domain.clientes.dto;

import com.eduardo.salaoDeBeleza.domain.clientes.Cliente;

public record DadosListagemCliente(Long id, String nome, String telefone){

    public DadosListagemCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone());
    }
}
