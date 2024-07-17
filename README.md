# ForumHub

ForumHub é uma API REST para gerenciamento de tópicos em um fórum. O projeto foi desenvolvido com Spring Boot e inclui funcionalidades de autenticação JWT, controle de acesso baseado em roles e operações CRUD para tópicos.

## Funcionalidades

- Criação de um novo tópico
- Listagem de todos os tópicos
- Detalhamento de um tópico específico
- Atualização de um tópico
- Exclusão de um tópico
- Autenticação JWT
- Controle de acesso baseado em roles

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Jakarta Validation
- JWT (JSON Web Token)
- PostgreSQL

## Configuração do Ambiente

### Pré-requisitos

- Java JDK 17 ou superior
- Maven 4 ou superior
- PostgreSQL 8 ou superior
- IDE (IntelliJ IDEA recomendado)

### Dependências

As dependências principais do projeto estão listadas no arquivo `pom.xml`. Aqui estão algumas das dependências mais importantes:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.18.1</version>
</dependency>
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
    <version>3.0.2</version>
</dependency>
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>7.0.1.Final</version>
</dependency>
```



