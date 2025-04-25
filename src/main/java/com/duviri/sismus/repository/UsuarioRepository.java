package com.duviri.sismus.repository;

import static com.duviri.sismus.entity.enums.PermissaoEnum.ADMINISTRADOR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duviri.sismus.dto.UsuarioDTO;
import com.duviri.sismus.entity.Usuario;
import com.duviri.sismus.entity.UsuarioBuilder;
import com.duviri.sismus.entity.UsuarioFactory;
import com.duviri.sismus.entity.adm.UsuarioAdministradorBuilder;
import com.duviri.sismus.entity.adm.UsuarioAdministradorFactory;
import com.duviri.sismus.entity.com.UsuarioComumBuilder;
import com.duviri.sismus.entity.com.UsuarioComumFactory;
import com.duviri.sismus.entity.enums.PermissaoEnum;
import com.duviri.sismus.util.ConnectionFactory;

public class UsuarioRepository {
    private static UsuarioRepository instance;

    private UsuarioRepository() {
        super();
    }

    public static UsuarioRepository getInstance() {
        if (instance == null) {
            instance = new UsuarioRepository();
        }
        return instance;
    }

    public void save(UsuarioDTO usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nome, email, endereco, telefone, senha, permissao) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        st.setString(1, usuario.nome());
        st.setString(2, usuario.email());
        st.setString(3, usuario.endereco());
        st.setString(4, usuario.telefone());
        st.setString(5, usuario.senha());
        st.setString(6, usuario.permissao());

        st.execute();

        st.close();
        conn.close();
    }

    public List<Usuario>  findAll() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        UsuarioBuilder builder;
        UsuarioBuilder builderComum = new UsuarioComumBuilder();
        UsuarioBuilder builderAdm = new UsuarioAdministradorBuilder();

        
        UsuarioFactory factory;
        UsuarioFactory factoryComum = new UsuarioComumFactory();
        UsuarioFactory factoryAdm = new UsuarioAdministradorFactory();

        List<Usuario> list = new ArrayList<>();
        while (rs.next()) {
            PermissaoEnum perm = PermissaoEnum.findByName(rs.getString("permissao"));
            
            builder = perm == ADMINISTRADOR ? builderAdm : builderComum;
            factory = perm == ADMINISTRADOR ? factoryAdm : factoryComum;
            
            builder = builder
                    .id(rs.getInt("id"))
                    .nome(rs.getString("nome"))
                    .email(rs.getString("email"))
                    .endereco(rs.getString("endereco"))
                    .senha(rs.getString("senha"))
                    .telefone(rs.getString("telefone"));
            list.add(factory.createUser(builder));
        }
        return list;
    }

}
