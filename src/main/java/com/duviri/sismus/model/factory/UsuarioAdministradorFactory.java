package com.duviri.sismus.model.factory;

import static com.duviri.sismus.model.enums.PermissaoEnum.ADMINISTRADOR;

import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.builder.UsuarioAdministradorBuilder;
import com.duviri.sismus.model.builder.UsuarioBuilder;

public class UsuarioAdministradorFactory extends UsuarioFactory {
    @Override
    public Usuario createUser(UsuarioBuilder builder) {
        if (!(builder instanceof UsuarioAdministradorBuilder)) {
            throw new IllegalArgumentException();
        }
        Usuario usuario = builder.build();
        usuario.setPermissao(ADMINISTRADOR);
        return usuario;
    }
}
