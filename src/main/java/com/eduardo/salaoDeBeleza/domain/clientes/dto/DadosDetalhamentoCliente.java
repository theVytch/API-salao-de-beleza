package com.eduardo.salaoDeBeleza.domain.clientes.dto;

import com.eduardo.salaoDeBeleza.domain.clientes.Cliente;

public record DadosDetalhamentoCliente(
        Long id,
        String nome,
        String telefone,
        String cpf,
        boolean ativo
) {

    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getCpf(), cliente.getAtivo());
    }
}
