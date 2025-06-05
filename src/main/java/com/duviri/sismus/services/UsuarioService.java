package com.duviri.sismus.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duviri.sismus.dao.UsuarioDao;
import com.duviri.sismus.dto.UsuarioDTO;
import com.duviri.sismus.dto.UsuarioUpdateDTO;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.builder.UsuarioAdministradorBuilder;
import com.duviri.sismus.model.builder.UsuarioBuilder;
import com.duviri.sismus.model.builder.UsuarioComumBuilder;
import com.duviri.sismus.model.enums.PermissaoEnum;
import com.duviri.sismus.model.factory.UsuarioAdministradorFactory;
import com.duviri.sismus.model.factory.UsuarioComumFactory;
import com.duviri.sismus.model.factory.UsuarioFactory;


@Service
public class UsuarioService {
    
    public UsuarioDao usuarioDao = UsuarioDao.getInstance();

    public void save(UsuarioDTO dto) throws SQLException {
        boolean isAdm = PermissaoEnum.findByName(dto.permissao()) == PermissaoEnum.ADMINISTRADOR;

        UsuarioBuilder builder = isAdm ? new UsuarioAdministradorBuilder() : new UsuarioComumBuilder();
        UsuarioFactory factory = isAdm ? new UsuarioAdministradorFactory() : new UsuarioComumFactory();

        Usuario usuario = factory.createUser(
            builder
                .nome(dto.nome())
                .email(dto.email())
                .endereco(dto.endereco())
                .telefone(dto.telefone())
                .senha(dto.senha())   
        );

        usuarioDao.save(usuario);
    }

    public List<Usuario> findAll() throws SQLException{
        return usuarioDao.findAll();
    }

    public Usuario findById(int id) throws SQLException {
        return usuarioDao.findById(id);
    }

    public void update(UsuarioUpdateDTO dto) throws SQLException {
        boolean isAdm = PermissaoEnum.findByName(dto.permissao()) == PermissaoEnum.ADMINISTRADOR;

        UsuarioBuilder builder = isAdm ? new UsuarioAdministradorBuilder() : new UsuarioComumBuilder();
        UsuarioFactory factory = isAdm ? new UsuarioAdministradorFactory() : new UsuarioComumFactory();

        Usuario usuario = factory.createUser(
            builder
                .id(dto.id())
                .nome(dto.nome())
                .email(dto.email())
                .endereco(dto.endereco())
                .telefone(dto.telefone())
                .senha(dto.senha())   
        );

        usuarioDao.update(usuario);
    }

    public void delete(int id) throws SQLException {
        usuarioDao.delete(id);
    }
}
