package com.duviri.sismus.controller.command.reservation;

import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.model.Reserva;
import com.duviri.sismus.services.ReservaService;

public class CancelReservationCommand<V> implements ICommand<V> {
    private V value;
    private ReservaService service = new ReservaService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            Reserva reserva = service.findById((int) value);
            service.cancel((int) value);
            mv.setViewName("redirect:/reservation/listbyuser/" + reserva.getUsuario().getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao cancelar a reserva");
            mv.setViewName("error");
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
