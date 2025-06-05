package com.duviri.sismus.controller.command.trip;

import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.dto.ViagemDTO;
import com.duviri.sismus.services.ViagemService;

public class SaveTripCommand<V> implements ICommand<V> {

    private V value;
    private ViagemService service = new ViagemService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            service.save((ViagemDTO) value);
            
            mv.setViewName("redirect:/trip/listbyuser/" + ((ViagemDTO) value).criador());
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Cadastrar");
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
