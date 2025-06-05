package com.duviri.sismus.controller.command.trip;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.IListByIdCommand;
import com.duviri.sismus.exceptions.ViagemNotFoundException;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.model.Viagem;
import com.duviri.sismus.services.ViagemService;

public class ListTripByIdCommand<V> implements IListByIdCommand<V> {
    private V id;
    private V value;
    private ViagemService service = new ViagemService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            Viagem viagem = service.findById((int) id);
            Usuario criador = viagem.getCriador();
            mv.addObject("criador", criador);
            mv.addObject("viagens", List.of(viagem));
            mv.setViewName("trip/list");
        } catch (ViagemNotFoundException ex) {
            try {
                mv.setViewName("trip/list");
                Usuario criador = service.findUserById((int) value);
                mv.addObject("criador", criador);
                mv.addObject("viagens", List.of());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Consultar Usuários");
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
