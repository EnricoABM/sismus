package com.duviri.sismus.repository;

import com.duviri.sismus.util.ConnectionFactory;
import com.duviri.sismus.entity.Usuario;
import com.duviri.sismus.entity.UsuarioBuilder;
import com.duviri.sismus.entity.UsuarioFactory;
import com.duviri.sismus.entity.adm.UsuarioAdministradorBuilder;
import com.duviri.sismus.entity.adm.UsuarioAdministradorFactory;
import com.duviri.sismus.entity.com.UsuarioComumBuilder;
import com.duviri.sismus.entity.com.UsuarioComumFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.duviri.sismus.entity.enums.PermissaoEnum.ADMINISTADOR;
import static com.duviri.sismus.entity.enums.PermissaoEnum.COMUM;

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

    public void save(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario VALUES (nome, email, endereco, telefone, senha, permissao) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setString(3, usuario.getEndereco());
        st.setString(4, usuario.getTelefone());
        st.setString(5, usuario.getSenha());
        st.setString(6, usuario.getPermissao().name());

        st.execute();

        st.close();
        conn.close();
    }

    public List<Usuario>  findAll() throws SQLException {
        List<Usuario> list = new ArrayList<>();
        list.addAll(this.findAllAdmins());
        list.addAll(this.findAllComuns());
        return list;
    }

    public List<Usuario>  findAllComuns() throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE permissao = ?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        st.setString(1, COMUM.name());

        ResultSet rs = st.executeQuery();
        List<Usuario> list = new ArrayList<>();
        UsuarioBuilder builder;
        Usuario usuario;

        UsuarioFactory factory = new UsuarioComumFactory();
        while (rs.next()) {
            builder = new UsuarioComumBuilder()
                    .id(rs.getInt("id"))
                    .nome(rs.getString("nome"))
                    .email(rs.getString("email"))
                    .endereco(rs.getString("endereco"))
                    .senha(rs.getString("senha"))
                    .telefone(rs.getString("telefone"));
            usuario = factory.createUser(builder);
            list.add(usuario);
        }
        return list;
    }

    public List<Usuario>  findAllAdmins() throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE permissao = ?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        st.setString(1, ADMINISTADOR.name());

        ResultSet rs = st.executeQuery();
        List<Usuario> list = new ArrayList<>();
        UsuarioBuilder builder;
        Usuario usuario;

        UsuarioFactory factory = new UsuarioAdministradorFactory();
        while (rs.next()) {
            builder = new UsuarioAdministradorBuilder()
                    .id(rs.getInt("id"))
                    .nome(rs.getString("nome"))
                    .email(rs.getString("email"))
                    .endereco(rs.getString("endereco"))
                    .senha(rs.getString("senha"))
                    .telefone(rs.getString("telefone"));
            usuario = factory.createUser(builder);
            list.add(usuario);
        }
        return list;
    }
}
