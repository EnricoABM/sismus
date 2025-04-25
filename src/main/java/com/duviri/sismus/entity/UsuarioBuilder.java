package com.duviri.sismus.entity;

public interface UsuarioBuilder {
    UsuarioBuilder id(int id);

    UsuarioBuilder nome(String nome);

    UsuarioBuilder email(String email);

    UsuarioBuilder endereco(String endereco);

    UsuarioBuilder telefone(String telefone);

    UsuarioBuilder senha(String senha);

    Usuario build();
}
