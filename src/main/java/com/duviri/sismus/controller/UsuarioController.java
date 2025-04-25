package com.duviri.sismus.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.dto.UsuarioDTO;
import com.duviri.sismus.entity.Usuario;
import com.duviri.sismus.repository.UsuarioRepository;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    
    UsuarioRepository repository = UsuarioRepository.getInstance();
    
    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        try {
            List<Usuario> usuarios = repository.findAll();
            mv.addObject("usuarios", usuarios);
            mv.setViewName("home");
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Consultar Usuários");
        }
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView newUser(UsuarioDTO dto) {
        ModelAndView mv = new ModelAndView();
        try {
            repository.save(dto);
            mv.setViewName("redirect:/user/home");
        } catch (SQLException ex) {
            mv.setViewName("error");
            mv.addObject("mensagem", "Erro ao Cadastrar");
        }
        return mv;
    }

}
