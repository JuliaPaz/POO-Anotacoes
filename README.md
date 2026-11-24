# 📝 Notas de Estudo com IA (Spring AI)

Sistema web simples feito com **Spring Boot + Spring AI**.
Você cadastra anotações de estudo e usa a GenAI para **resumir**, **gerar perguntas de estudo** e **perguntar sobre o conteúdo** da nota.

## Requisitos atendidos
- ✅ Integração com banco de dados (Spring Data JPA + H2)
- ✅ Interface Web (Thymeleaf)
- ✅ Integração com GenAI via Spring AI (`ChatClient`)
- ✅ Funcionalidades que utilizam a GenAI (resumo, perguntas, chat sobre a nota)

## Tecnologias
Java 17 · Spring Boot 3.4 · Spring AI 1.0 · Thymeleaf · Spring Data JPA · H2

## Como executar
1. Crie uma chave de API em https://platform.openai.com/api-keys
2. Defina a chave como **variável de ambiente** (ela NÃO vai para o GitHub):

   Linux/Mac:
   ```bash
   export OPENAI_API_KEY=sua-chave-aqui
   ```
   Windows (PowerShell):
   ```powershell
   $env:OPENAI_API_KEY="sua-chave-aqui"
   ```
3. Rode o projeto:
   ```bash
   mvn spring-boot:run
   ```
4. Acesse http://localhost:8080

Console do banco (opcional): http://localhost:8080/h2-console
(JDBC URL: `jdbc:h2:file:./data/notasdb`, usuário `sa`, senha vazia)

## Estrutura
```
src/main/java/com/exemplo/notasia
├── NotasIaApplication.java
├── model/Nota.java
├── repository/NotaRepository.java
├── service/IaService.java        <- integração com a GenAI
└── controller/NotaController.java
src/main/resources
├── application.properties
└── templates/index.html
```

## Segurança
A chave da API é lida de `OPENAI_API_KEY` e nunca é escrita no código.


## IA local com Ollama

Instale o Ollama e baixe o modelo usado pelo projeto:

```powershell
ollama run qwen2.5:3b
```

Mantenha o Ollama em execução e inicie a aplicação pelo Maven (`spring-boot:run`). A integração utiliza `http://localhost:11434` e não exige chave de API.
