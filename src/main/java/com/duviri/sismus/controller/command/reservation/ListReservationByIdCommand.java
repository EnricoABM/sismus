package com.duviri.sismus.controller.command.reservation;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.IListByIdCommand;
import com.duviri.sismus.model.ReservaCompleta;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.services.ReservaService;

public class ListReservationByIdCommand<V> implements IListByIdCommand<V>{
    private V id;
    private V value;
    private ReservaService service = new ReservaService();

    @Override
    public ModelAndView execute() {
 ModelAndView mv = new ModelAndView();
        try {
            Usuario user = service.findUser((int) value);
            ReservaCompleta reserva = service.findById((int) id, (int) value);
            mv.addObject("usuario", user);
            mv.addObject("reservas", List.of(reserva));
            mv.setViewName("reservation/list");
        } catch (RuntimeException ex) {
            mv.setViewName("reservation/list");
            Usuario user;
            try {
                user = service.findUser((int) value);
                mv.addObject("usuario", user);
                mv.addObject("reservas", List.of());
            } catch (SQLException e) {
                ex.printStackTrace();
                mv.setViewName("error");
                mv.addObject("mensagem", "Erro ao consultar a reserva");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao consultar a reserva");
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

    @Override
    public V getId() {
        return id;
    }

    @Override
    public void setId(V id) {
        this.id = id;
    }

}
