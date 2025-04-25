package com.duviri.sismus.entity;

import com.duviri.sismus.entity.enums.PermissaoEnum;

public interface Usuario {
    int getId();

    void setId(int id);

    String getNome();

    void setNome(String nome);

    String getEmail();

    void setEmail(String email);

    String getEndereco();

    void setEndereco(String endereco);

    String getTelefone();

    void setTelefone(String telefone);

    String getSenha();

    void setSenha(String senha);

    PermissaoEnum getPermissao();

    void setPermissao(PermissaoEnum permissao);

}
