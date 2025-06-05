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