package com.duviri.sismus.controller.command.reservation;

import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.dto.ReservaDTO;
import com.duviri.sismus.services.ReservaService;

public class SaveReservationCommand<V> implements ICommand<V> {
    private V value;
    private ReservaService service = new ReservaService();

    @Override
    public ModelAndView execute() {
      ModelAndView mv = new ModelAndView();
        try {
            service.save((ReservaDTO) value);
            mv.setViewName("redirect:/reservation/listbyuser/" + ((ReservaDTO) value).usuario());
        } catch (SQLException e) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao cadastrar o usuário");
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