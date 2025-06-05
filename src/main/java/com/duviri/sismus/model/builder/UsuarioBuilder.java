package com.duviri.sismus.model.builder;

import com.duviri.sismus.model.Usuario;

public interface UsuarioBuilder {
    UsuarioBuilder id(int id);

    UsuarioBuilder nome(String nome);

    UsuarioBuilder email(String email);

    UsuarioBuilder endereco(String endereco);

    UsuarioBuilder telefone(String telefone);

    UsuarioBuilder senha(String senha);

    Usuario build();
}
