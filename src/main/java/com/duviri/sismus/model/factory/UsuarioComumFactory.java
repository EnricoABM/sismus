package com.duviri.sismus.model.factory;

import static com.duviri.sismus.model.enums.PermissaoEnum.COMUM;

import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.builder.UsuarioBuilder;
import com.duviri.sismus.model.builder.UsuarioComumBuilder;

public class UsuarioComumFactory extends UsuarioFactory {
    @Override
    public Usuario createUser(UsuarioBuilder builder) {
        if (!(builder instanceof UsuarioComumBuilder)) {
            throw new IllegalArgumentException();
        }
        Usuario usuario = builder.build();
        usuario.setPermissao(COMUM);
        return usuario;
    }
}
