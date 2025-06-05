package com.duviri.sismus.model;

public class Viagem {
    int id;
    String titulo;
    String origem;
    String destino;
    double valor;
    boolean disponivel;
    int passagensTotais;
    int passagensDisponiveis;
    Usuario criador;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    public int getPassagensTotais() { return passagensTotais; }
    public void setPassagensTotais(int passagensTotais) { this.passagensTotais = passagensTotais; }
    public int getPassagensDisponiveis() { return passagensDisponiveis; }
    public void setPassagensDisponiveis(int passagensDisponiveis) { this.passagensDisponiveis = passagensDisponiveis; }
    public Usuario getCriador() {
        return criador;
    }
    public void setCriador(Usuario criador) {
        this.criador = criador;
    }
     
}
