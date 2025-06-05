package com.duviri.sismus.controller.command.user;

import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.dto.UsuarioUpdateDTO;
import com.duviri.sismus.services.UsuarioService;

public class UpdateUserCommand<V> implements ICommand<V> {
    private V value;
    private UsuarioService service = new UsuarioService();

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView();

        try {
            service.update((UsuarioUpdateDTO) value);
            mv.setViewName("redirect:/user/listall");
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Cadastrar");
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
