package com.eduardo.salaoDeBeleza.domain.trabalhos;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Funcionario;
import com.eduardo.salaoDeBeleza.domain.trabalhos.dto.DadosAtualizacaoTrabalho;
import com.eduardo.salaoDeBeleza.domain.trabalhos.dto.DadosCadastroTrabalho;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "trabalhos")
@Entity(name = "Trabalho")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Trabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Trabalho(DadosCadastroTrabalho dados, Funcionario funcionario){
        this.nome = dados.nome();
        this.valor = dados.valor();
        this.funcionario = funcionario;
    }

    public void atualizarInformacoes(DadosAtualizacaoTrabalho dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.valor() != null) {
            this.valor = dados.valor();
        }
    }
}
