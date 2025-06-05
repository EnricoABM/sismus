package com.duviri.sismus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duviri.sismus.dto.ReservaDTO;
import com.duviri.sismus.exceptions.ReservaNotFoundException;
import com.duviri.sismus.model.Reserva;
import com.duviri.sismus.model.ReservaCompleta;
import com.duviri.sismus.util.ConnectionFactory;

public class ReservaDao {
    private static ReservaDao instance;

    private ReservaDao() {
    }

    public static ReservaDao getInstance() {
        if (instance == null) {
            instance = new ReservaDao();
        }
        return instance;
    }

    public List<Reserva> findAll() throws SQLException {
        String sql = "SELECT * FROM Reserva";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        List<Reserva> reservas = new ArrayList<>();
        while (rs.next()) {
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("id"));
            reserva.setUsuario(UsuarioDao.getInstance().findById(rs.getInt("usuario_id")));
            reserva.setViagem(ViagemDao.getInstance().findById(rs.getInt("viagem_id")));
            reservas.add(reserva);
        }

        return reservas;
    }

    public List<ReservaCompleta> joinQuerry() throws SQLException {
        String sql = """
                SELECT
                    r.id id,
                    u.nome nome,
                    u.telefone telefone,
                    v.titulo titulo,
                    v.origem origem,
                    v.destino destino
                FROM Reserva r
                LEFT JOIN Usuario u
                ON r.usuario = u.id
                LEFT JOIN Viagem v
                ON r.viagem = v.id;
                """;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        List<ReservaCompleta> reservas = new ArrayList<>();
        while (rs.next()) {
            ReservaCompleta reserva = new ReservaCompleta();

            reserva.setId(rs.getInt("id"));
            reserva.setNome(rs.getString("nome"));
            reserva.setTelefone(rs.getString("telefone"));
            reserva.setTitulo(rs.getString("titulo"));
            reserva.setOrigem(rs.getString("origem"));
            reserva.setDestino(rs.getString("destino"));

            reservas.add(reserva);
        }

        return reservas;
    }

    public List<ReservaCompleta> findByUser(int id) throws SQLException {
        String sql = """
                SELECT
                    r.id id,
                    u.nome nome,
                    u.telefone telefone,
                    v.titulo titulo,
                    v.origem origem,
                    v.destino destino
                FROM Reserva r
                LEFT JOIN Usuario u
                ON r.usuario = u.id
                LEFT JOIN Viagem v
                ON r.viagem = v.id
                WHERE u.id = ?;
                """;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        List<ReservaCompleta> reservas = new ArrayList<>();
        while (rs.next()) {
            ReservaCompleta reserva = new ReservaCompleta();

            reserva.setId(rs.getInt("id"));
            reserva.setNome(rs.getString("nome"));
            reserva.setTelefone(rs.getString("telefone"));
            reserva.setTitulo(rs.getString("titulo"));
            reserva.setOrigem(rs.getString("origem"));
            reserva.setDestino(rs.getString("destino"));

            reservas.add(reserva);
        }
        return reservas;
    }

    public void save(ReservaDTO dto) throws SQLException {
        String sql = "INSERT INTO Reserva (usuario, viagem) VALUES (?, ?)";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, dto.usuario());
        st.setInt(2, dto.viagem());

        st.execute();

        st.close();

    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Reserva WHERE id = ?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.execute();

        conn.close();
    }

    public Reserva findById(int id) throws SQLException {
        String sql = "SELECT * FROM Reserva WHERE id = ?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Reserva reserva = new Reserva();
        if (rs.next()) {
            reserva.setId(rs.getInt("id"));
            reserva.setUsuario(UsuarioDao.getInstance().findById(rs.getInt("usuario")));
            reserva.setViagem(ViagemDao.getInstance().findById(rs.getInt("viagem")));
        } else {
            throw new ReservaNotFoundException("Reserva não encontrada");
        }
        return reserva;

    }

}
