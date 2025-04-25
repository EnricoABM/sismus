package com.duviri.sismus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/find")
    public String findAll() {
        return "list";
    }

}
