package com.duviri.sismus.model;

public class ReservaCompleta {
    private int id;
    private String nome;
    private String telefone;
    private String titulo;
    private String origem;
    private String destino;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeUsuario) {
        this.nome = nomeUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefoneUsuario) {
        this.telefone = telefoneUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String tituloViagem) {
        this.titulo = tituloViagem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origemViagem) {
        this.origem = origemViagem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destinoViagem) {
        this.destino = destinoViagem;
    }
}
