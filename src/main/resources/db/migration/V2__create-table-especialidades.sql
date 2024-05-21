CREATE TABLE funcionarios_especialidade (
    funcionario_id BIGINT NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    PRIMARY KEY (funcionario_id, especialidade),
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);
