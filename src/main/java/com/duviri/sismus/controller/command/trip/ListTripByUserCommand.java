package com.duviri.sismus.controller.command.trip;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.Viagem;
import com.duviri.sismus.services.ViagemService;

public class ListTripByUserCommand<V> implements ICommand<V> {
    private V value;
    private ViagemService service = new ViagemService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            List<Viagem> viagens = service.findByUser((int) value);
            Usuario usuario = service.findUserById((int) value);
            mv.addObject("criador", usuario);
            mv.addObject("viagens", viagens);
            mv.setViewName("trip/list");
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Consultar Viagens");
            ex.printStackTrace();
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
