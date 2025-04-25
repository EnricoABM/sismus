package com.duviri.sismus.entity.com;

import com.duviri.sismus.entity.Usuario;
import com.duviri.sismus.entity.UsuarioBuilder;
import com.duviri.sismus.entity.UsuarioFactory;

import static com.duviri.sismus.entity.enums.PermissaoEnum.COMUM;

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
