CREATE TABLE Reserva(
    id int not null primary key auto_increment,
    usuario int not null,
    viagem int not null,
    FOREIGN KEY (usuario) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (viagem) REFERENCES Viagem(id) ON DELETE CASCADE ON UPDATE CASCADE
);