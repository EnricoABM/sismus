package com.duviri.sismus.controller.command;

import org.springframework.web.servlet.ModelAndView;

public interface ICommand<V> {
    ModelAndView execute();
    V getValue();
    void setValue(V value);
}
