package com.duviri.sismus.controller.command.user;

import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.services.UsuarioService;

public class DeleteUserCommand<V> implements ICommand<V> {
    private V value;
    private UsuarioService service = new UsuarioService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();
        try {
            service.delete((int) value);
            mv.setViewName("redirect:/user/listall");
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Excluir Usuário");
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
