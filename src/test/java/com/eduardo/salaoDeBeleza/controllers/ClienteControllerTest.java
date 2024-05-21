package com.eduardo.salaoDeBeleza.controllers;

import com.eduardo.salaoDeBeleza.domain.funcionarios.Especialidade;
import com.eduardo.salaoDeBeleza.domain.funcionarios.Funcionario;
import com.eduardo.salaoDeBeleza.domain.funcionarios.dto.DadosCadastroFuncionario;
import com.eduardo.salaoDeBeleza.domain.funcionarios.dto.DadosDetalhamentoFuncionarios;
import com.eduardo.salaoDeBeleza.repositories.FuncionarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosDetalhamentoFuncionarios> dadosDetalhamentoFuncionariosJson;

    @Autowired
    private JacksonTester<DadosCadastroFuncionario> dadosCadastroFuncionarioJson;

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve devolver codigo http 201 quando informações estão validas")
    @WithMockUser(roles = {"ADMIN"})
    void cadastrar_cenario1() throws Exception {
        List<Especialidade> esp = new ArrayList<>();
        esp.addAll(Arrays.asList(Especialidade.BARBEIRO, Especialidade.CABELEIREIRO));
        var dadosCadastroFuncionario = new DadosCadastroFuncionario(
                "Funcionario",
                "41 977775555",
                "funcionario@gmail.com",
                "11199977725",
                esp

        );

        when(funcionarioRepository.save(any())).thenReturn(new Funcionario(dadosCadastroFuncionario));

        var response = mvc
                .perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroFuncionarioJson.write(dadosCadastroFuncionario).getJson())).andReturn().getResponse();

        var dadosDetalhamento = new DadosDetalhamentoFuncionarios(null,
                dadosCadastroFuncionario.nome(),
                dadosCadastroFuncionario.telefone(),
                dadosCadastroFuncionario.email(),
                dadosCadastroFuncionario.cpf(),
                dadosCadastroFuncionario.especialidade(),
                new ArrayList<>()
        );

        var jsonEsperado = dadosDetalhamentoFuncionariosJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informações estao invalidas")
    @WithMockUser(roles = {"ADMIN"})
    void cadastrar_cenario2() throws Exception {
        DadosCadastroFuncionario dadosInvalidos = new DadosCadastroFuncionario(null, null, null, null, null);

        // Enviar uma solicitação POST com o objeto inválido no corpo
        MvcResult result = mvc.perform(post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosInvalidos)))
                .andExpect(status().isBadRequest()) // Verificar se o status esperado é 400
                .andReturn();
    }
}