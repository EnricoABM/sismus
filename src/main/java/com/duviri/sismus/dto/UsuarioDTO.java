package com.duviri.sismus.dto;

public record UsuarioDTO(
    String nome,
    String email,
    String endereco,
    String telefone,
    String senha,
    String permissao
) {
    
}
