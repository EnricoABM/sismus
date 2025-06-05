package com.duviri.sismus.controller.command.trip;

import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.dto.ViagemUpdateDTO;
import com.duviri.sismus.services.ViagemService;

public class UpdateTripCommand<V> implements ICommand<V> {
    private V value;
    private ViagemService service = new ViagemService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();

        try {
            service.update((ViagemUpdateDTO) value);
            mv.setViewName("redirect:/trip/listbyuser/" + ((ViagemUpdateDTO) value).criador());
        } catch (RuntimeException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", ex.getMessage());
        } catch (SQLException e) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Atualizar Viagem");
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
