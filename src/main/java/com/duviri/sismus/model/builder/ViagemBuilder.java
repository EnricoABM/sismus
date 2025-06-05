package com.duviri.sismus.model.builder;

import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.Viagem;

public class ViagemBuilder {
    private Viagem viagem = new Viagem();

    public ViagemBuilder id(int id) {
        viagem.setId(id);
        return this;
    }

    public ViagemBuilder titulo(String titulo) {
        viagem.setTitulo(titulo);
        return this;
    }

    public ViagemBuilder origem(String origem) {
        viagem.setOrigem(origem);
        return this;
    }

    public ViagemBuilder destino(String destino) {
        viagem.setDestino(destino);
        return this;
    }

    public ViagemBuilder valor(double valor) {
        viagem.setValor(valor);
        return this;
    }

    public ViagemBuilder disponivel(boolean disponivel) {
        viagem.setDisponivel(disponivel);
        return this;
    }

    public ViagemBuilder passagensTotais(int passagensTotais) {
        viagem.setPassagensTotais(passagensTotais);
        return this;
    };

    public ViagemBuilder passagensDisponiveis(int passagensDisponiveis) {
        viagem.setPassagensDisponiveis(passagensDisponiveis);
        return this;
    }

    public ViagemBuilder criador(Usuario criador) {
        viagem.setCriador(criador);
        return this;
    }

    public Viagem build() {
        return viagem;
    }
}
