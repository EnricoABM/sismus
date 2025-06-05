package com.duviri.sismus.controller.command;

public interface IListByIdCommand<V> extends ICommand<V> {
    V getId();

    void setId(V id);
}
