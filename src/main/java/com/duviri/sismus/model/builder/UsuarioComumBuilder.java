package com.duviri.sismus.model.builder;

import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.UsuarioComum;

public class UsuarioComumBuilder implements UsuarioBuilder {
    private int id;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String senha;

    @Override
    public UsuarioBuilder id(int id) {
        this.id = id;
        return this;
    }

    @Override
    public UsuarioBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public UsuarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UsuarioBuilder endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    @Override
    public UsuarioBuilder telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    @Override
    public UsuarioBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    @Override
    public Usuario build() {
        return new UsuarioComum(id, nome, email, endereco, telefone, senha);
    }
}
