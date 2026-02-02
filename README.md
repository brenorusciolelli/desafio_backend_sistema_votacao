# Desafio Backend – Sistema de Votação

## 📌 Visão Geral

Este projeto implementa uma API REST para gerenciamento de **pautas** e **sessões de votação**, permitindo que associados votem **SIM** ou **NÃO** em uma pauta dentro de uma sessão com tempo determinado.

A solução foi desenvolvida em **Java + Spring Boot**, priorizando clareza, boas práticas, simplicidade e possibilidade de evolução arquitetural.

---

## 🧱 Tecnologias Utilizadas

* **Java 17+** (compatível com Java 21)
* **Spring Boot**
* **Spring Web (MVC)**
* **Spring Data JPA**
* **Bean Validation (Jakarta Validation)**
* **H2 Database (em memória)**
* **Lombok**
* **Swagger / OpenAPI**
* **Docker**

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
Controller  →  Service  →  Repository  →  Database
```

### Responsabilidades

* **Controller**: expõe os endpoints REST e valida entradas
* **Service**: concentra regras de negócio e validações
* **Repository**: acesso a dados via Spring Data JPA
* **Entity**: mapeamento das tabelas do banco
* **Exception Handler**: tratamento centralizado de erros

---

## 📦 Modelo de Domínio

### Pauta

* Representa o tema da votação
* Possui título, descrição e data de criação

### Sessão de Votação

* Associada a uma pauta
* Possui horário de início e fim
* Controla se a sessão está aberta ou encerrada

### Associado

* Identificado unicamente por CPF
* Pode votar apenas **uma vez por sessão**

### Voto

* Relaciona associado + sessão
* Valor do voto: `YES` ou `NOT`

---

## 🔄 Fluxo da Aplicação

1. Criar uma **Pauta**
2. Abrir uma **Sessão de Votação** para a pauta
3. Associados enviam seus votos durante o período da sessão
4. Consultar o **resultado da votação** após o encerramento

---

## 🧠 Regras de Negócio Implementadas

* Uma sessão possui tempo padrão de **1 minuto**, caso não informado
* Não é possível votar em sessão encerrada
* Um associado só pode votar **uma única vez por sessão**
* CPF identifica de forma única o associado
* Validações de entrada usando `@Valid`

---

## ❗ Tratamento de Erros

O projeto utiliza um **Global Exception Handler** com `@ControllerAdvice` para:

* Padronizar respostas de erro
* Retornar mensagens claras de validação
* Evitar stacktraces expostos ao cliente

### Exemplos de Erros Tratados

* 400 – Erro de validação (campos nulos ou inválidos)
* 404 – Recurso não encontrado
* 409 – Regras de negócio violadas (ex: voto duplicado)

---

## 📄 Validação de Dados

Utilizamos **Jakarta Bean Validation**:

```java
@NotNull(message = "Title cannot be null")
@NotBlank(message = "Title cannot be empty")
```

As mensagens são retornadas de forma amigável ao consumidor da API.

---

## 📑 Documentação da API (Swagger)

A API conta com documentação automática via **Swagger UI**.

Após subir a aplicação:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Banco de Dados

* Banco **H2 em memória** para facilitar execução e testes
* Console disponível em:

```
http://localhost:8080/h2-console
```

Configuração via `application.properties`.

---

## 🐳 Docker

O projeto pode ser executado via Docker.

### Build da imagem

```bash
docker build -t desafio-backend .
```

### Execução

```bash
docker run -p 8080:8080 desafio-backend
```

---

📌 **Autor**: Breno Rusciolelhe
