package com.duviri.sismus.controller.command.reservation;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.model.ReservaCompleta;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.services.ReservaService;

public class ListReservationByUserCommand<V> implements ICommand<V> {
    private V value;
    private ReservaService service = new ReservaService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            List<ReservaCompleta> reservas = service.findByUser((int) value);
            Usuario usuario = service.findUser((int) value);

            mv.addObject("reservas", reservas);
            mv.addObject("usuario", usuario);
            mv.setViewName("reservation/list");
        } catch (SQLException ex) {
            ex.printStackTrace();
            mv.setViewName("error");
            mv.addObject("message", ex.getMessage());
            return mv;
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
