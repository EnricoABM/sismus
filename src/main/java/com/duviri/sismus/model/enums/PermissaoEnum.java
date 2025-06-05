package com.duviri.sismus.model.enums;

import java.util.stream.Stream;

public enum PermissaoEnum {
    COMUM,
    ADMINISTRADOR;

    public static PermissaoEnum findByName(String name) {
        return Stream.of(PermissaoEnum.values()).filter(p -> p.name().equalsIgnoreCase(name.toUpperCase())).findFirst().get();
    }
}
