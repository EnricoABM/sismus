package com.duviri.sismus.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duviri.sismus.dao.UsuarioDao;
import com.duviri.sismus.dao.ViagemDao;
import com.duviri.sismus.dto.ViagemDTO;
import com.duviri.sismus.dto.ViagemUpdateDTO;
import com.duviri.sismus.exceptions.ViagemNotFoundException;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.Viagem;
import com.duviri.sismus.model.builder.ViagemBuilder;
import com.duviri.sismus.model.enums.PermissaoEnum;

@Service
public class ViagemService {

    private ViagemDao viagemDao = ViagemDao.getInstance();

    public void save(ViagemDTO dto) throws SQLException {
        Usuario criador = UsuarioDao.getInstance().findById(dto.criador());

        Viagem viagem = new ViagemBuilder()
                .titulo(dto.titulo())
                .origem(dto.origem())
                .destino(dto.destino())
                .valor(dto.valor())
                .passagensTotais(dto.passagensTotais())
                .passagensDisponiveis(dto.passagensTotais())
                .disponivel(true)
                .criador(criador)
                .build();

        viagemDao.save(viagem);
    }

    public void update(ViagemUpdateDTO dto) throws SQLException, ViagemNotFoundException {
        Usuario criador = UsuarioDao.getInstance().findById(dto.criador());

        Viagem viagem = viagemDao.findById(dto.id());

        int reservadas = viagem.getPassagensTotais() - viagem.getPassagensDisponiveis();

        int novoTot = Math.max(reservadas, dto.passagensTotais());
        int novoDisp = novoTot - reservadas;

        Viagem viagemAtt = new ViagemBuilder()
                .id(dto.id())
                .titulo(dto.titulo())
                .origem(dto.origem())
                .destino(dto.destino())
                .valor(dto.valor())
                .passagensTotais(novoTot)
                .passagensDisponiveis(novoDisp)
                .disponivel(novoDisp > 0) 
                .criador(criador)
                .build();

        viagemDao.update(viagemAtt);
    }

    public List<Viagem> findAll() throws SQLException {
        return viagemDao.findAll();
    }

    public Viagem findById(int id) throws SQLException {
        return viagemDao.findById(id);
    }

    public void delete(int id) throws SQLException {
        viagemDao.delete(id);
    }

    public List<Usuario> findAllAdmUsers() throws SQLException {
        UsuarioDao usuarioDao = UsuarioDao.getInstance();
        List<Usuario> usuarios = usuarioDao.findAll();
        usuarios = usuarios.stream()
                .filter(usuario -> usuario.getPermissao() == PermissaoEnum.ADMINISTRADOR)
                .toList();
        return new ArrayList<>(usuarios);
    }

    public List<Viagem> findByUser(int id) throws SQLException {
        return viagemDao.findByUser(id);
    }

    public Usuario findUserById(int id) throws SQLException {
        return UsuarioDao.getInstance().findById(id);
    }

}
