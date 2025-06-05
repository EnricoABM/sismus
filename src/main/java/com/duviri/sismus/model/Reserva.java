package com.duviri.sismus.model;

public class Reserva {
    private int id;
    private Usuario usuario;
    private Viagem viagem;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Viagem getViagem() {
        return viagem;
    }
    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }
}
