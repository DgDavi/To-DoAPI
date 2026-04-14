# To-DoAPP API

API REST para gerenciamento de usuários e tarefas, com autenticação JWT, Spring Security, Spring Data JPA e banco H2 em memória.

## Tecnologias

- Java 26
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT (`com.auth0:java-jwt`)
- H2 Database
- Maven

## Como executar

1. Defina uma chave JWT (opcional, existe valor padrão no `application.properties`):

```powershell
$env:JWT_SECRET="sua_chave_forte"
```

2. Rode a aplicação:

```powershell
.\mvnw.cmd spring-boot:run
```

3. A API sobe por padrão em:

```text
http://localhost:8080
```

4. Console H2:

```text
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:todoapp
User: sa
Password: (vazio)
```

## Autenticação

- Rotas públicas:
  - `POST /users/register`
  - `POST /users/login`
- Demais rotas exigem token JWT no header:

```text
Authorization: Bearer <token>
```

## Endpoints

| Método | Rota | Auth | Descrição |
|---|---|---|---|
| POST | `/users/register` | Não | Cadastra usuário |
| POST | `/users/login` | Não | Autentica e retorna token |
| GET | `/users/read` | Sim | Retorna usuário autenticado |
| PATCH | `/users/edit` | Sim | Atualiza `name` e/ou `email` do usuário autenticado |
| POST | `/tasks` | Sim | Cria tarefa |

## Exemplos de payload

### `POST /users/register`

```json
{
  "name": "Davi",
  "email": "davi@email.com",
  "password": "senha1234"
}
```

### `POST /users/login`

```json
{
  "email": "davi@email.com",
  "password": "senha1234"
}
```

### `PATCH /users/edit`

```json
{
  "name": "Davi Silva",
  "email": "davi.silva@email.com"
}
```

### `POST /tasks`

```json
{
  "title": "Estudar Spring Security",
  "description": "Revisar autenticação com JWT"
}
```

## Regras de validação principais

- Cadastro exige:
  - `name` e `email` não vazios
  - email válido
  - senha com pelo menos 8 caracteres, letras e números
- Edição de usuário exige ao menos um campo (`name` ou `email`)
- E-mail duplicado retorna conflito (`409`)
