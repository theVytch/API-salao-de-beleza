package com.eduardo.salaoDeBeleza.domain.funcionarios;

import com.eduardo.salaoDeBeleza.domain.funcionarios.dto.DadosAtualizacaoFuncionario;
import com.eduardo.salaoDeBeleza.domain.funcionarios.dto.DadosCadastroFuncionario;
import com.eduardo.salaoDeBeleza.domain.trabalhos.Trabalho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "funcionarios")
@Entity(name = "Funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;

    @OneToMany(mappedBy = "funcionario")
    private List<Trabalho> trabalho;
    private boolean ativo;

    @ElementCollection
    @CollectionTable(name = "funcionarios_especialidade", joinColumns = @JoinColumn(name = "funcionario_id"))
    @Enumerated(EnumType.STRING) // Indica que os enums serão armazenados como strings
    @Column(name = "especialidade")
    private List<Especialidade> especialidade = new ArrayList<>();


    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.trabalho = new ArrayList<>();
        this.ativo = true;
        this.especialidade = dados.especialidade().stream().toList();
    }

    public void atualizarInformacoes(DadosAtualizacaoFuncionario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }

        if (dados.email() != null) {
            this.email = dados.email();
        }

        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }

        if (!dados.especialidade().isEmpty()) {
            this.especialidade = dados.especialidade();
        }
    }
    public void inativar() {
        this.ativo = false;
    }
}
