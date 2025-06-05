package com.duviri.sismus.dto;

public record ViagemUpdateDTO(
        int id,
        String titulo,
        String origem,
        String destino,
        double valor,
        int passagensTotais,
        int criador) {
}
