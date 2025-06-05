package com.duviri.sismus.dto;

public record UsuarioUpdateDTO(
    int id,
    String nome,
    String email,
    String endereco,
    String telefone,
    String senha,
    String permissao 
) {
    
}
