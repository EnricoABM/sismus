package com.duviri.sismus.entity.adm;

import com.duviri.sismus.entity.Usuario;
import com.duviri.sismus.entity.UsuarioBuilder;
import com.duviri.sismus.entity.UsuarioFactory;

import static com.duviri.sismus.entity.enums.PermissaoEnum.ADMINISTRADOR;

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
