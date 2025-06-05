package com.duviri.sismus.dto;


public record ViagemDTO(
    String titulo,
    String origem,
    String destino,
    double valor,
    int passagensTotais,
    int criador
) {
    
}
