![Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/img/banner-logo.svg)

# Oli Saúde Backend Developer Challenge

_java-health-risks_ é uma __API REST__ criada com a linguagem de programação Java e o framework Spring, desenvolvida a partir do [desafio backend _público_ da Oli Saúde](https://github.com/olisaude/teste-dev-backend).

[![License](https://img.shields.io/npm/l/react)](https://github.com/ordanael/java-health-risks/blob/main/LICENSE) 

## Tecnologias

- Java
- Spring Boot
- Banco de dados H2 e PostgreSQL
- JPA & Hibernate
- Docker

## Funcionalidades

- CRUD das entidades
- Adicionar e remover uma doença no cliente
- Buscar pelos dez clientes com maiores riscos de saúde
- Testes automatizados
- Paginação e filtros de busca

## Diagrama Entidade-Relacionamento

![DER](https://i.ibb.co/KzQGgzK/der-drawio.png)

## Execução

### Com Docker

> Requisitos: Docker

```shell
# Baixar a imagem do Docker Hub
docker pull ordanael/java-health-risks:1.0.0

# Criar um container a partir da imagem, expôr a porta 8080 e nomear o container
docker run -p 8080:8080 --name java-health-risks ordanael/java-health-risks:1.0.0

# A aplicação já está em execução neste ponto, utilize os comandos abaixo para desligar a aplicação

# Parar o container
docker stop java-health-risks

# Iniciar um container que está parado
docker start java-health-risks

# Remover container
docker rm java-health-risks

# Remover imagem
docker rmi ordanael/java-health-risks:1.0.0
```

### Com Git

> Requisitos: Git e Java17

```shell
# Clonar o repositório git
git clone https://github.com/ordanael/java-health-risks.git

# Entrar na pasta do repositório
cd java-health-risks

# Iniciar a aplicação
./mvnw spring-boot:run
```

# Endpoints

| Método | Rota                                         | Funcionalidade                                        | Payload                                                          |
|--------|----------------------------------------------|-------------------------------------------------------|------------------------------------------------------------------|
| POST   | /disease                                     | Cria uma nova doença                                  | {"name": "string", "grade": number}                              |
| GET    | /disease/{id}                                | Busca uma doença por ID                               |                                                                  |
| GET    | /disease                                     | Busca por todas as doenças                            |                                                                  |
| PATCH  | /disease/{id}                                | Atualiza uma doença                                   | {"grade": number}                                                |
| DELETE | /disease/{id}                                | Deleta uma doença                                     |                                                                  |
| POST   | /customer                                    | Cria um novo cliente                                  | {"name": "string", "birthDay": "yyyy-mm-dd", "gender": "string"} |
| GET    | /customer/{id}                               | Busca um cliente por ID                               |                                                                  |
| GET    | /customer                                    | Busca por todos os clientes                           |                                                                  |
| PUT    | /customer/{id}                               | Atualiza um cliente                                   | {"name": "string", "birthDay": "yyyy-mm-dd", "gender": "string"} |
| DELETE | /customer/{id}                               | Deleta um cliente                                     |                                                                  |
| POST   | /customer/{customer_id}/disease/{disease_id} | Adiciona uma doença ao cliente                        |                                                                  |
| DELETE | /customer/{customer_id}/disease/{disease_id} | Remove uma doença do cliente                          |                                                                  |
| GET    | /customer/disease                            | Recupera dez clientes com os maiores indíces de risco |                                                                  |
