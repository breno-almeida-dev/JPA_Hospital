# 🏥 Sistema Hospitalar - API REST com Spring Boot, DTO e PostgreSQL

Este projeto foi desenvolvido em **Java com Spring Boot** como parte das atividades práticas da disciplina de **Back-End**.

O objetivo principal é simular um **sistema hospitalar**, aplicando conceitos fundamentais de desenvolvimento de APIs REST, arquitetura em camadas, persistência com JPA/Hibernate, uso de DTOs e integração com banco de dados PostgreSQL em nuvem utilizando o **Neon Serverless Postgres**.

---

## 🚀 Sobre o Projeto

A aplicação representa um sistema hospitalar com entidades relacionadas entre si, permitindo o gerenciamento de:

- Pacientes
- Prontuários
- Médicos
- Convênios
- Consultas
- Receitas

A API permite realizar operações de **CRUD completo** para as entidades do sistema, utilizando uma estrutura organizada em camadas:

```text
Controller → DTO → Service → Repository → Banco de Dados
```

---

## 🧠 Evolução do Projeto

Inicialmente, o projeto foi desenvolvido utilizando banco de dados em memória **H2**, com foco no aprendizado de mapeamento de entidades JPA.

Na versão atual, o projeto foi evoluído para uma estrutura mais próxima de uma aplicação real, utilizando:

- DTOs para entrada e saída de dados
- PostgreSQL em nuvem com Neon
- Controllers mais limpos
- Services responsáveis pela regra de negócio
- Repositories para persistência
- Entidades JPA relacionadas entre si

---

## 🏗️ Arquitetura Utilizada

O projeto segue uma arquitetura em camadas:

```text
src/main/java/com/example/hospital
│
├── controller
│   ├── PacienteController.java
│   ├── MedicoController.java
│   ├── ConvenioController.java
│   ├── ProntuarioController.java
│   ├── ConsultaController.java
│   └── ReceitaController.java
│
├── dto
│   ├── PacienteRequestDTO.java
│   ├── PacienteResponseDTO.java
│   ├── MedicoRequestDTO.java
│   ├── MedicoResponseDTO.java
│   ├── ConvenioRequestDTO.java
│   ├── ConvenioResponseDTO.java
│   ├── ProntuarioRequestDTO.java
│   ├── ProntuarioResponseDTO.java
│   ├── ConsultaRequestDTO.java
│   ├── ConsultaResponseDTO.java
│   ├── ReceitaRequestDTO.java
│   └── ReceitaResponseDTO.java
│
├── model
│   ├── Paciente.java
│   ├── Medico.java
│   ├── Convenio.java
│   ├── Prontuario.java
│   ├── Consulta.java
│   └── Receita.java
│
├── repository
│   ├── PacienteRepository.java
│   ├── MedicoRepository.java
│   ├── ConvenioRepository.java
│   ├── ProntuarioRepository.java
│   ├── ConsultaRepository.java
│   └── ReceitaRepository.java
│
└── service
    ├── PacienteService.java
    ├── MedicoService.java
    ├── ConvenioService.java
    ├── ProntuarioService.java
    ├── ConsultaService.java
    └── ReceitaService.java
```

---

## 📌 Entidades do Sistema

### 1. Paciente

Representa os pacientes cadastrados no sistema hospitalar.

**Atributos principais:**

- id
- nome
- cpf
- telefone

**Relacionamentos:**

- Um paciente possui um prontuário
- Um paciente pode possuir várias consultas

---

### 2. Prontuário

Armazena informações médicas do paciente.

**Atributos principais:**

- id
- tipoSanguineo
- alergia
- observacoes

**Relacionamento:**

- Um prontuário pertence a um paciente

No DTO, o relacionamento é feito utilizando:

```json
{
  "pacienteId": 1
}
```

---

### 3. Médico

Representa os médicos cadastrados no hospital.

**Atributos principais:**

- id
- nome
- especialidade
- crm

**Relacionamento:**

- Um médico pode realizar várias consultas

---

### 4. Convênio

Representa os convênios médicos aceitos pelo hospital.

**Atributos principais:**

- id
- nome
- cnpj

**Relacionamento:**

- Um convênio pode estar associado a várias consultas

---

### 5. Consulta

Representa uma consulta médica realizada no hospital.

**Atributos principais:**

- id
- dataHora
- motivo
- valor

**Relacionamentos:**

- Uma consulta pertence a um paciente
- Uma consulta pertence a um médico
- Uma consulta pode estar associada a um convênio
- Uma consulta pode possuir uma receita

No DTO, os relacionamentos são feitos utilizando IDs:

```json
{
  "dataHora": "2026-04-26T19:30:00",
  "motivo": "Dor de cabeça",
  "valor": 150.0,
  "pacienteId": 1,
  "medicoId": 1,
  "convenioId": 1
}
```

---

### 6. Receita

Representa uma prescrição médica vinculada a uma consulta.

**Atributos principais:**

- id
- medicamento
- dosagem
- duracaoDias

**Relacionamento:**

- Uma receita pertence a uma consulta

No DTO, o relacionamento é feito utilizando:

```json
{
  "consultaId": 1
}
```

---

## 🔗 Relacionamentos JPA Implementados

O projeto utiliza os principais relacionamentos do JPA:

- `@OneToOne`
- `@OneToMany`
- `@ManyToOne`
- `@JoinColumn`
- `mappedBy`

Representação geral dos relacionamentos:

```text
Paciente 1 ─── 1 Prontuario

Paciente 1 ─── N Consulta

Medico 1 ─── N Consulta

Convenio 1 ─── N Consulta

Consulta 1 ─── 1 Receita
```

---

## 🔄 Uso de DTO

O projeto utiliza o padrão **DTO (Data Transfer Object)** para separar os dados recebidos e retornados pela API das entidades do banco de dados.

Foram criados dois tipos principais de DTO para cada entidade:

- `RequestDTO`: usado para receber dados nas requisições
- `ResponseDTO`: usado para devolver dados nas respostas

### Vantagens do uso de DTO

- Evita exposição direta das entidades JPA
- Reduz acoplamento entre API e banco de dados
- Evita problemas de serialização JSON
- Evita loops infinitos em relacionamentos bidirecionais
- Torna as requisições mais simples
- Facilita a manutenção do projeto

---

## ☁️ Banco de Dados

A versão atual do projeto utiliza **Neon Serverless Postgres**, um banco PostgreSQL em nuvem.

A conexão é feita por meio do arquivo:

```text
src/main/resources/application.properties
```

Exemplo de configuração:

```properties
spring.application.name=hospital

spring.datasource.url=jdbc:postgresql://HOST_DO_NEON/neondb?sslmode=require&channel_binding=require
spring.datasource.username=USUARIO
spring.datasource.password=SENHA

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.minimum-idle=1
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.connection-timeout=20000
```

> Por segurança, dados sensíveis como usuário, senha e host do banco não devem ser expostos publicamente no repositório.

---

## 🧪 Testes com Postman

A API pode ser testada utilizando o **Postman** ou ferramentas semelhantes, como Insomnia.

A URL base da aplicação local é:

```text
http://localhost:8080
```

---

## 📍 Endpoints da API

### Pacientes

```http
GET     /pacientes
GET     /pacientes/{id}
POST    /pacientes
PUT     /pacientes/{id}
DELETE  /pacientes/{id}
```

Exemplo de JSON para cadastro:

```json
{
  "nome": "João Silva",
  "cpf": "12345678900",
  "telefone": "15999999999"
}
```

---

### Médicos

```http
GET     /medicos
GET     /medicos/{id}
POST    /medicos
PUT     /medicos/{id}
DELETE  /medicos/{id}
```

Exemplo de JSON para cadastro:

```json
{
  "nome": "Dra. Ana Souza",
  "especialidade": "Cardiologia",
  "crm": "CRM-SP-123456"
}
```

---

### Convênios

```http
GET     /convenios
GET     /convenios/{id}
POST    /convenios
PUT     /convenios/{id}
DELETE  /convenios/{id}
```

Exemplo de JSON para cadastro:

```json
{
  "nome": "Unimed Sorocaba",
  "cnpj": "12345678000199"
}
```

---

### Prontuários

```http
GET     /prontuarios
GET     /prontuarios/{id}
POST    /prontuarios
PUT     /prontuarios/{id}
DELETE  /prontuarios/{id}
```

Exemplo de JSON para cadastro:

```json
{
  "tipoSanguineo": "O+",
  "alergia": "Dipirona",
  "observacoes": "Paciente com histórico de pressão alta",
  "pacienteId": 1
}
```

---

### Consultas

```http
GET     /consultas
GET     /consultas/{id}
POST    /consultas
PUT     /consultas/{id}
DELETE  /consultas/{id}
```

Exemplo de JSON para cadastro:

```json
{
  "dataHora": "2026-04-26T19:30:00",
  "motivo": "Dor de cabeça",
  "valor": 150.0,
  "pacienteId": 1,
  "medicoId": 1,
  "convenioId": 1
}
```

---

### Receitas

```http
GET     /receitas
GET     /receitas/{id}
POST    /receitas
PUT     /receitas/{id}
DELETE  /receitas/{id}
```

Exemplo de JSON para cadastro:

```json
{
  "medicamento": "Paracetamol",
  "dosagem": "500mg a cada 8 horas",
  "duracaoDias": 5,
  "consultaId": 1
}
```

---

## ✅ Ordem Recomendada para Testes

Como algumas entidades dependem de outras, recomenda-se testar a API nesta ordem:

```text
1. Paciente
2. Médico
3. Convênio
4. Prontuário
5. Consulta
6. Receita
```

Essa ordem garante que os IDs necessários para os relacionamentos já existam no banco de dados.

---

## ⚙️ Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```

### 2. Acessar a pasta do projeto

```bash
cd hospital
```

### 3. Configurar o banco de dados

Edite o arquivo:

```text
src/main/resources/application.properties
```

E configure sua conexão com o banco PostgreSQL do Neon.

### 4. Executar a aplicação

No Windows:

```bash
mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

Após iniciar, a API estará disponível em:

```text
http://localhost:8080
```

---

## 📋 Pré-requisitos

Para executar este projeto, é necessário ter instalado:

- Java JDK 21 ou superior
- Maven ou Maven Wrapper
- Git
- IDE de sua preferência:
  - Eclipse
  - IntelliJ IDEA
  - VS Code
- Conta no Neon para uso do PostgreSQL em nuvem
- Postman ou Insomnia para testes da API

---

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Neon Serverless Postgres
- Maven
- Postman
- Git
- GitHub

---

## 📚 Conceitos Praticados

Este projeto aplica os seguintes conceitos de desenvolvimento Back-End:

- Criação de API REST
- Arquitetura em camadas
- Separação de responsabilidades
- DTO Pattern
- Mapeamento objeto-relacional
- Persistência com Spring Data JPA
- Relacionamentos entre entidades
- Uso de repositories
- Uso de services
- Uso de controllers
- Integração com banco de dados em nuvem
- Testes de endpoints com Postman

---

## 📌 Versão Atual

### Versão 2.0

Esta versão representa a evolução do projeto para uma estrutura mais robusta, utilizando:

- DTOs para entrada e saída de dados
- Banco PostgreSQL em nuvem
- Neon Serverless Postgres
- CRUD completo para as principais entidades
- Relacionamentos JPA entre as tabelas
- Organização em camadas

---

## ✒️ Autor

**Breno Gustavo Rocha de Almeida**

Projeto desenvolvido como atividade prática da disciplina de **Back-End**, com foco no aprendizado de Spring Boot, JPA, DTO, arquitetura REST e integração com banco de dados relacional em nuvem.
