# Sistema Hospitalar - API REST com Spring Boot (JPA)

Este projeto foi desenvolvido em **Java utilizando Spring Boot**, com o objetivo de praticar o **mapeamento de entidades com JPA (Java Persistence API)** e a criação de relacionamentos entre classes em um sistema hospitalar.

A aplicação simula um ambiente hospitalar contendo entidades como **Paciente, Prontuário, Médico, Convênio, Consulta e Receita**, permitindo compreender como os dados se relacionam entre si em um banco de dados relacional.

Este projeto faz parte das atividades práticas da disciplina de **Back-End**, com foco em compreender como funciona o **ORM (Object Relational Mapping)** utilizando **Spring Data JPA e Hibernate**.

---

# 🚀 Começando

O objetivo deste projeto é demonstrar, na prática, como modelar um sistema real utilizando **JPA no Spring Boot**, criando:

- Entidades com `@Entity`
- Relacionamentos entre tabelas
- Configuração de banco de dados em memória (H2)
- Geração automática de tabelas pelo Hibernate

A aplicação permite visualizar a estrutura do banco e seus relacionamentos através do console H2.

---

# 📌 Funcionalidades Implementadas

A API possui as seguintes entidades e relacionamentos:

---

### 1️⃣ Paciente

Representa os pacientes do sistema hospitalar.

Atributos:

- id
- nome
- cpf
- telefone

Relacionamentos:

- Possui **um prontuário (OneToOne)**
- Possui **várias consultas (OneToMany)**

---

### 2️⃣ Prontuário

Armazena informações médicas do paciente.

Atributos:

- id
- tipo sanguíneo
- alergia
- observações

Relacionamento:

- Pertence a um paciente (**OneToOne**)

---

### 3️⃣ Médico

Representa os médicos do sistema.

Atributos:

- id
- nome
- especialidade
- crm

Relacionamento:

- Um médico realiza várias consultas (**OneToMany**)

---

### 4️⃣ Convênio

Representa os convênios médicos.

Atributos:

- id
- nome
- cnpj

Relacionamento:

- Um convênio cobre várias consultas (**OneToMany**)

---

### 5️⃣ Consulta

Representa atendimentos realizados no hospital.

Atributos:

- id
- dataHora
- motivo
- valor

Relacionamentos:

- Pertence a um paciente (**ManyToOne**)
- Pertence a um médico (**ManyToOne**)
- Pertence a um convênio (**ManyToOne**)
- Possui uma receita (**OneToOne**)

---

### 6️⃣ Receita

Representa prescrições médicas.

Atributos:

- id
- medicamento
- dosagem
- duração em dias

Relacionamento:

- Pertence a uma consulta (**OneToOne**)

---

# 🔗 Relacionamentos JPA Implementados

Este projeto utiliza os seguintes tipos de relacionamento:

- `@OneToOne`
- `@OneToMany`
- `@ManyToOne`

Também foram aplicados conceitos importantes como:

- Lado dono do relacionamento (`@JoinColumn`)
- Lado inverso (`mappedBy`)
- Relacionamentos bidirecionais

---

# 🗄️ Banco de Dados (H2)

A aplicação utiliza o banco de dados **H2 em memória**, com:

- Criação automática de tabelas
- Console web habilitado

Acesse o console:

http://localhost:8080/h2-console

Configurações:

JDBC URL: jdbc:h2:mem:hospitaldb  
User: sa  
Password: 123

---

# 📋 Pré-requisitos

Para executar este projeto em sua máquina, você precisará ter instalado:

- Java JDK 21 ou superior
- Maven ou Maven Wrapper
- VS Code ou Eclipse IDE
- Git
- Conta no GitHub

---

# 🛠️ Construído com

Tecnologias utilizadas neste projeto:

- Java SE
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Banco H2
- Maven
- VS Code / Eclipse IDE
- Git
- GitHub

---

# 📌 Versão

Versão atual do projeto:

Versão 1.0

Primeira implementação do sistema hospitalar com foco em **mapeamento JPA e relacionamentos entre entidades**.

---

# 📚 Conceitos praticados

Este projeto explora os seguintes conceitos de desenvolvimento Back-End:

- Criação de entidades com JPA
- Mapeamento objeto-relacional (ORM)
- Relacionamentos entre tabelas
- Uso de `@Entity`, `@Id`, `@GeneratedValue`
- Uso de `@OneToOne`, `@OneToMany`, `@ManyToOne`
- Uso de `@JoinColumn` e `mappedBy`
- Configuração de banco H2
- Geração automática de tabelas com Hibernate
- Estrutura de projeto Spring Boot

---

# ✒️ Autor

Breno Gustavo Rocha de Almeida

Projeto desenvolvido como atividade da disciplina de Back-End, com foco no aprendizado de Spring Boot, JPA e modelagem de banco de dados relacional.
