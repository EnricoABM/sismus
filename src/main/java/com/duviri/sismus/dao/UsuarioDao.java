package com.duviri.sismus.dao;

import static com.duviri.sismus.model.enums.PermissaoEnum.ADMINISTRADOR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duviri.sismus.exceptions.UsuarioNotFoundException;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.builder.UsuarioAdministradorBuilder;
import com.duviri.sismus.model.builder.UsuarioBuilder;
import com.duviri.sismus.model.builder.UsuarioComumBuilder;
import com.duviri.sismus.model.enums.PermissaoEnum;
import com.duviri.sismus.model.factory.UsuarioAdministradorFactory;
import com.duviri.sismus.model.factory.UsuarioComumFactory;
import com.duviri.sismus.model.factory.UsuarioFactory;
import com.duviri.sismus.util.ConnectionFactory;

public class UsuarioDao {
    private static UsuarioDao instance;

    private UsuarioDao() {
        super();
    }

    public static UsuarioDao getInstance() {
        if (instance == null) {
            instance = new UsuarioDao();
        }
        return instance;
    }

    public void save(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nome, email, endereco, telefone, senha, permissao) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setString(3, usuario.getEndereco());
        st.setString(4, usuario.getTelefone());
        st.setString(5, usuario.getSenha());
        st.setString(6, usuario.getPermissao().toString());

        st.execute();

        st.close();
        conn.close();
    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET nome=?, email=?, endereco=?, telefone=?, senha=?, permissao=? WHERE id=?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setString(3, usuario.getEndereco());
        st.setString(4, usuario.getTelefone());
        st.setString(5, usuario.getSenha());
        st.setString(6, usuario.getPermissao().toString());
        st.setInt(7, usuario.getId());

        st.execute();

        st.close();
        conn.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id=?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.execute();

        conn.close();
    }

    public List<Usuario> findAll() throws SQLException {
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

    public Usuario findById(int id) throws SQLException, UsuarioNotFoundException {
        String sql = "SELECT * FROM Usuario WHERE id=?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        UsuarioBuilder builder = null;
        UsuarioBuilder builderComum = new UsuarioComumBuilder();
        UsuarioBuilder builderAdm = new UsuarioAdministradorBuilder();
        
        UsuarioFactory factory = null;
        UsuarioFactory factoryComum = new UsuarioComumFactory();
        UsuarioFactory factoryAdm = new UsuarioAdministradorFactory();
        
        Usuario user = null;

        if (rs.next()) {
            PermissaoEnum permissao = PermissaoEnum.findByName(rs.getString("permissao"));

            if (permissao == ADMINISTRADOR) {
                builder = builderAdm;
                factory = factoryAdm;
            } else {
                builder = builderComum;
                factory = factoryComum;
            }

            builder = builder
                    .id(rs.getInt("id"))
                    .nome(rs.getString("nome"))
                    .email(rs.getString("email"))
                    .endereco(rs.getString("endereco"))
                    .telefone(rs.getString("telefone"))
                    .senha(rs.getString("senha"));
        } else {
            throw new UsuarioNotFoundException();
        }

        user = factory.createUser(builder);

        return user;
    }

}
