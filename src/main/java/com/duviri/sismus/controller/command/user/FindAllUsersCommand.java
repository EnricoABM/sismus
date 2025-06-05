package com.duviri.sismus.controller.command.user;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.model.Usuario;
import com.duviri.sismus.services.UsuarioService;

public class FindAllUsersCommand<V> implements ICommand<V> {
    
    private V value;
    private UsuarioService service = new UsuarioService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            List<Usuario> usuarios = service.findAll();
            mv.addObject("usuarios", usuarios);
            mv.setViewName("index");
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
