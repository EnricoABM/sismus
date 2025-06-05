package com.duviri.sismus.controller.command.reservation;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.Viagem;
import com.duviri.sismus.services.ReservaService;

public class SaveReservationFormCommand<V> implements ICommand<V> {
    private V value;
    private ReservaService service = new ReservaService();

    @Override
    public ModelAndView execute() {
                ModelAndView mv = new ModelAndView();
        try {
            Usuario usuario = service.findUser((int) value);
            List<Viagem> viagens = service.findViagemDisp();
            mv.addObject("usuario", usuario);
            mv.addObject("viagens", viagens);
            mv.setViewName("reservation/create");
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao consultar o usuário");
        }
        return mv;
    }
    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }

    
}
