package com.duviri.sismus.controller.command.trip;

import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.Viagem;
import com.duviri.sismus.services.ViagemService;

public class UpdateTripFormCommand<V> implements ICommand<V>{
    private V value;
    private ViagemService service = new ViagemService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            Viagem viagem = service.findById((int) value);
            Usuario criador = viagem.getCriador();

            mv.addObject("viagem", viagem);
            mv.addObject("criador", criador);
            mv.setViewName("trip/update");
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Consultar Usuário");
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
