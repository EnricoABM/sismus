package com.duviri.sismus.controller.command.trip;

import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.model.Viagem;
import com.duviri.sismus.services.ViagemService;

public class DeleteTripCommand<V> implements ICommand<V> {
    private V value;
    private ViagemService service = new ViagemService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            Viagem viagem = service.findById((int) value);
            service.delete(viagem.getId());
            mv.setViewName("redirect:/trip/listbyuser/" + viagem.getCriador().getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Excluir Viagem");
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
