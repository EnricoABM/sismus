# Projeto Sismus

Sistema para gerenciamento de uma agência de viagens, permitindo cadastro e reserva de viagens pelos seus usuários. O projeto visa demonstrar o uso dos padrões de projetos descritos pelo Gang Of 4. 

# Tecnologias
* Java.
* Spring MVC. 
* Thymeleaf.
* MySQL.

# Padrões Utilizados
* Builder - Utilizado para facilitar a criação dos objetos ao longo do sistema.
* Factory - Utilizado para criação de novos usuários, conforme sua permissão.
* Singleton - Utilizado para facilitar a interação com o banco de dados, implementados nas classes DAO.
* Command - Utilizado para separação das principais ações realizadas pelos controllers.

# Como executar

* Dependências: Java e Banco de Dados MYSQL.

1. Crie as entidades do banco de dados. 
```sql
CREATE DATABASE sismus;

USE sismus;

CREATE TABLE Usuario (
    id int primary key auto_increment,
    nome varchar(255) not null,
    email varchar(255) not null,
    endereco varchar(255) not null,
    telefone varchar(255) not null,
    senha varchar(255) not null,
    permissao Enum("COMUM", "ADMINISTRADOR") not null
);

CREATE TABLE Viagem (
    id int not null PRIMARY KEY AUTO_INCREMENT,
    titulo varchar(255) not null,
    origem varchar(255) not null,
    destino varchar(255) not null,
    valor decimal(7, 2) not null,
    disponivel boolean not null,
    passagensTotais int not null,
    passagensDisponiveis int not null,
    criador int not null,
    FOREIGN KEY (criador) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Reserva(
    id int not null primary key auto_increment,
    usuario int not null,
    viagem int not null,
    FOREIGN KEY (usuario) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (viagem) REFERENCES Viagem(id) ON DELETE CASCADE ON UPDATE CASCADE
);
```

2. Abra a classe `ConnectionFactory` e altere as propriedades a seguir:
```
String USER = "CHANGEME";
String PASS = "CHANGEME";
String URL = "jdbc:mysql://localhost:3306/sismus";
``` 

3. Execute a classe `SismusApplication.java`.

4. Acesse a URL: `http:/localhost:8080/`

