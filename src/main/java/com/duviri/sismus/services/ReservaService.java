package com.duviri.sismus.services;

import java.sql.SQLException;
import java.util.List;

import com.duviri.sismus.dao.UsuarioDao;
import com.duviri.sismus.dao.ViagemDao;
import com.duviri.sismus.dto.ReservaDTO;
import com.duviri.sismus.model.Viagem;
import org.springframework.stereotype.Service;

import com.duviri.sismus.dao.ReservaDao;
import com.duviri.sismus.model.Reserva;
import com.duviri.sismus.model.ReservaCompleta;
import com.duviri.sismus.model.Usuario;

@Service
public class ReservaService {

    public List<ReservaCompleta> findAll() throws SQLException {
        return ReservaDao.getInstance().joinQuerry();
    }

    public List<ReservaCompleta> findByUser(int id) throws SQLException {
        return ReservaDao.getInstance().findByUser(id);
    }

    public Usuario findUser(int id) throws SQLException {
        return UsuarioDao.getInstance().findById(id);
    }

    public List<Viagem> findViagemDisp() throws SQLException {
        return ViagemDao.getInstance().findAll().stream().filter(Viagem::isDisponivel).toList();
    }

    public void save(ReservaDTO dto) throws SQLException {
        Viagem viagem = ViagemDao.getInstance().findById(dto.viagem());

        if (viagem.getPassagensDisponiveis() <= 0) {
            throw new RuntimeException("Não há passagens disponíveis para esta viagem.");
        }

        if (viagem.isDisponivel() == true && viagem.getPassagensDisponiveis() == 1) {
            ViagemDao.getInstance().changeDisp(false, viagem.getId());
        }

        ViagemDao.getInstance().decreseDisp(dto.viagem());

        ReservaDao.getInstance().save(dto);
    }

    public void cancel(int id) throws SQLException {
        Reserva reserva = ReservaDao.getInstance().findById(id);

        Viagem viagem = ViagemDao.getInstance().findById(reserva.getViagem().getId());

        if (viagem.getPassagensDisponiveis() >= viagem.getPassagensTotais()) {
            throw new RuntimeException("Limite máximo atendido");
        }

        if (viagem.isDisponivel() == false) {
            ViagemDao.getInstance().changeDisp(true, reserva.getViagem().getId());
        }

        ViagemDao.getInstance().increseDisp(reserva.getViagem().getId());
        ReservaDao.getInstance().delete(id);
    }

    public Reserva findById(int id) throws SQLException {
        Reserva reserva = ReservaDao.getInstance().findById(id);
        return reserva;
    }

    public ReservaCompleta findById(int id, int usuario) throws SQLException {
        List<ReservaCompleta> reservas = ReservaDao.getInstance().findByUser(usuario);

        return reservas.stream().filter((r) -> r.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    }
}
