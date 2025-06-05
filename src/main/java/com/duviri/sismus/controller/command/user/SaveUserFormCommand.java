package com.duviri.sismus.controller.command.user;

import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;

public class SaveUserFormCommand<V> implements ICommand<V> {

    private V value;

    @Override
    public ModelAndView execute() {
        ModelAndView mv = new ModelAndView("user/create");
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
