package com.eduardo.salaoDeBeleza.domain.clientes;

import com.eduardo.salaoDeBeleza.domain.clientes.dto.DadosAtualizacaoCliente;
import com.eduardo.salaoDeBeleza.domain.clientes.dto.DadosCadastroCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String cpf;
    private Boolean ativo;

    public Cliente(DadosCadastroCliente dados){
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoCliente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
