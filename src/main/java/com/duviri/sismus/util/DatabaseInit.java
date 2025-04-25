package com.duviri.sismus.util;

import com.duviri.sismus.entity.enums.PermissaoEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class DatabaseInit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String senha;
    @Enumerated(EnumType.STRING)
    private PermissaoEnum permissao;
}
