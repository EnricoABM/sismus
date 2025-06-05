package com.duviri.sismus.controller.command.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.exceptions.UsuarioNotFoundException;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.services.UsuarioService;

public class ListUserByIdCommand<V> implements ICommand<V> {
    
    private V value;
    private UsuarioService service = new UsuarioService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            Usuario usuario = service.findById((int) value);
            mv.addObject("usuarios", List.of(usuario));
            mv.setViewName("index");
        } catch (UsuarioNotFoundException ex) {
            mv.setViewName("index");
            mv.addObject("usuarios", new ArrayList<Usuario>());
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Consultar Usuários");
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
