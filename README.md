# Salão de beleza

Projeto feito para praticar Spring Boot 3.

## Sumário

- [Sobre](#sobre)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Usar](#como-usar)
- [Pré-requisitos](#Pré-requisitos)
- [Instalação](#Instalação)

## Sobre

A API foi desenvolvida com o objetivo de simular um sistema de gestão de um salão de beleza. Seu propósito é permitir que os usuários possam agendar serviços, cadastrar funcionários e clientes, proporcionando uma experiência prática e eficiente. Este projeto visa aprimorar minhas habilidades como desenvolvedor Java, fornecendo uma aplicação robusta e de fácil utilização para os usuários finais.

Além disso, o sistema permite o cadastro de usuários, garantindo o controle de acesso às diferentes funcionalidades da aplicação por meio de autenticação e autorização. Dessa forma, assegura-se que apenas usuários devidamente registrados possam acessar e gerenciar as informações do salão, aumentando a segurança e a personalização do serviço.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Tokens)
- MySQL
- Flyway
- Lombok
- SpringDoc OpenAPI (para documentação da API)
- Swagger UI

## Como Usar

- Importe o projeto em sua IDE.
- Configure as propriedades do banco de dados no arquivo `application.properties`.
- Execute a aplicação Spring Boot.

- Acesse a documentação da API gerada automaticamente pelo Swagger UI em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html#/) para acessar os endpoint.
- Realize requisições HTTP para a API conforme documentado.

### Pré-requisitos

- Java 17 ou superior;

### Instalação

1. Clone este repositório.
   ```bash
   git clone https://github.com/theVytch/API-salao-de-beleza.git

2. Adicione o seguinte script ao seu banco de dados para gerar um usuário e obter um token para realizar as requisições.
   ```bash
   insert into usuarios values (1, 'admin@gmail.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ROLE_ADMIN')

3. Utilize as seguintes credenciais para autenticação:
```bash
   {
      "login": "admin@gmail.com",
      "senha": 123456
   }
