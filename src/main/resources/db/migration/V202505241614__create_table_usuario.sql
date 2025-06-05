create table Usuario (
    id int primary key auto_increment,
    nome varchar(255) not null,
    email varchar(255) not null,
    endereco varchar(255) not null,
    telefone varchar(255) not null,
    senha varchar(255) not null,
    permissao Enum("COMUM", "ADMINISTRADOR") not null
)