create table funcionarios(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    telefone varchar(20),
    email varchar(100) not null unique,
    cpf varchar(11) not null,
    ativo tinyint,
    primary key(id)
);

create table trabalhos(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    valor double,
    funcionario_id bigint,
    primary key(id),
    foreign key (funcionario_id) references funcionarios(id)
);

create table clientes(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    telefone varchar(20) not null,
    cpf varchar(11) not null,
    ativo tinyint,
    primary key(id)
);

create table agendamentos(
    id bigint not null auto_increment,
    funcionario_id bigint not null,
    cliente_id bigint not null,
    data datetime not null,
    primary key(id),
    constraint fk_agendamentos_funcionario_id foreign key(funcionario_id) references funcionarios(id),
    constraint fk_agendamentos_cliente_id foreign key(cliente_id) references clientes(id)
);