package com.duviri.sismus.model.factory;

import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.builder.UsuarioBuilder;

public abstract class UsuarioFactory {
    public abstract Usuario createUser(UsuarioBuilder builder);
}
