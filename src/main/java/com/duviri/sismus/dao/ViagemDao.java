package com.duviri.sismus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duviri.sismus.exceptions.ViagemNotFoundException;
import com.duviri.sismus.model.Viagem;
import com.duviri.sismus.model.builder.ViagemBuilder;
import com.duviri.sismus.util.ConnectionFactory;

public class ViagemDao {
     private static ViagemDao instance;

    private ViagemDao() {
        super();
    }

    public static ViagemDao getInstance() {
        if (instance == null) {
            instance = new ViagemDao();
        }
        return instance;
    }

    public void save(Viagem viagem) throws SQLException {
        String sql = "INSERT INTO Viagem (titulo, origem, destino, valor, disponivel, passagensTotais, passagensDisponiveis, criador) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, viagem.getTitulo());
        st.setString(2, viagem.getOrigem());
        st.setString(3, viagem.getDestino());
        st.setDouble(4, viagem.getValor());
        st.setBoolean(5, viagem.isDisponivel());
        st.setInt(6, viagem.getPassagensTotais());
        st.setInt(7, viagem.getPassagensTotais());
        st.setInt(8, viagem.getCriador().getId());

        st.execute();

        conn.close();
    }

    public void update(Viagem viagem) throws SQLException {
        String sql = "UPDATE Viagem SET titulo=?, origem=?, destino=?, valor=?, disponivel=?, criador=?, passagensTotais=?, passagensDisponiveis=? WHERE id=?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, viagem.getTitulo());
        st.setString(2, viagem.getOrigem());
        st.setString(3, viagem.getDestino());
        st.setDouble(4, viagem.getValor());
        st.setBoolean(5, viagem.isDisponivel());
        st.setInt(6, viagem.getCriador().getId());
        st.setInt(7, viagem.getPassagensTotais());
        st.setInt(8, viagem.getPassagensDisponiveis());
        st.setInt(9, viagem.getId());

        st.execute();

        conn.close();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Viagem WHERE id=?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.execute();

        conn.close();
    }

    public Viagem findById(int id) throws SQLException, ViagemNotFoundException {
        String sql = "SELECT * FROM Viagem WHERE id=?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();
        UsuarioDao usuarioDao = UsuarioDao.getInstance();
        Viagem viagem = new ViagemBuilder().build();
        if(rs.next()) {
            viagem = new ViagemBuilder()
                .id(id)
                .titulo(rs.getString("titulo"))
                .origem(rs.getString("origem"))
                .destino(rs.getString("destino"))
                .valor(rs.getDouble("valor"))
                .disponivel(rs.getBoolean("disponivel"))
                .criador(usuarioDao.findById(rs.getInt("criador")))
                .passagensTotais(rs.getInt("passagensTotais"))
                .passagensDisponiveis(rs.getInt("passagensDisponiveis"))
                .build();
        } else {
            throw new ViagemNotFoundException();
        }

        conn.close();
        return viagem;
    }

    public List<Viagem> findAll() throws SQLException {
        String sql = "SELECT * FROM Viagem";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        List<Viagem> list = new ArrayList<>();
        UsuarioDao usuarioDao = UsuarioDao.getInstance();
        while(rs.next()) {
            Viagem viagem = new ViagemBuilder()
                    .id(rs.getInt("id"))
                    .titulo(rs.getString("titulo"))
                    .origem(rs.getString("origem"))
                    .destino(rs.getString("destino"))
                    .valor(rs.getDouble("valor"))
                    .disponivel(rs.getBoolean("disponivel"))
                    .criador(usuarioDao.findById(rs.getInt("criador")))
                    .passagensTotais(rs.getInt("passagensTotais"))
                    .passagensDisponiveis(rs.getInt("passagensDisponiveis"))
                    .build();

            list.add(viagem);
        }

        conn.close();
        return list;

    }

    public void decreseDisp(int id) throws SQLException {
        String sql = "UPDATE Viagem SET passagensDisponiveis = passagensDisponiveis - 1 WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.execute();

        conn.close();
    }

    public void increseDisp(int id) throws SQLException {
        String sql = "UPDATE Viagem SET passagensDisponiveis = passagensDisponiveis + 1 WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.execute();

        conn.close();
    }

    public List<Viagem> findByUser(int id) throws SQLException {
        String sql = "SELECT * FROM Viagem WHERE criador = ?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        List<Viagem> list = new ArrayList<>();
        UsuarioDao usuarioDao = UsuarioDao.getInstance();
        while(rs.next()) {
            Viagem viagem = new ViagemBuilder()
                    .id(rs.getInt("id"))
                    .titulo(rs.getString("titulo"))
                    .origem(rs.getString("origem"))
                    .destino(rs.getString("destino"))
                    .valor(rs.getDouble("valor"))
                    .disponivel(rs.getBoolean("disponivel"))
                    .criador(usuarioDao.findById(rs.getInt("criador")))
                    .passagensTotais(rs.getInt("passagensTotais"))
                    .passagensDisponiveis(rs.getInt("passagensDisponiveis"))
                    .build();

            list.add(viagem);
        }

        conn.close();
        return list;
    }

    public void changeDisp(boolean disp, int id) throws SQLException {
        String sql = "UPDATE Viagem SET disponivel = ? WHERE id = ?";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setBoolean(1, disp);
        st.setInt(2, id);

        st.execute();

        conn.close();
    }
}
