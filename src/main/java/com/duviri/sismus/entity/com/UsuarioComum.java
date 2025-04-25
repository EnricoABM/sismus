package com.duviri.sismus.entity.com;

import com.duviri.sismus.entity.Usuario;
import com.duviri.sismus.entity.enums.PermissaoEnum;

public class UsuarioComum implements Usuario {
    private int id;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String senha;
    private PermissaoEnum permissao;

    public UsuarioComum(int id, String nome, String email, String endereco, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.senha = senha;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    @Override
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public PermissaoEnum getPermissao() {
        return permissao;
    }

    @Override
    public void setPermissao(PermissaoEnum permissao) {
        this.permissao = permissao;
    }

}
