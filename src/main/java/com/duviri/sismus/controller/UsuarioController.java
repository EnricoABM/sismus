package com.duviri.sismus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.controller.command.user.DeleteUserCommand;
import com.duviri.sismus.controller.command.user.FindAllUsersCommand;
import com.duviri.sismus.controller.command.user.ListUserByIdCommand;
import com.duviri.sismus.controller.command.user.SaveUserCommand;
import com.duviri.sismus.controller.command.user.SaveUserFormCommand;
import com.duviri.sismus.controller.command.user.UpdateUserCommand;
import com.duviri.sismus.controller.command.user.UpdateUserFormCommand;
import com.duviri.sismus.dto.UsuarioDTO;
import com.duviri.sismus.dto.UsuarioUpdateDTO;


@Controller
@RequestMapping("/user")
public class UsuarioController {


    @GetMapping("/listall")
    public ModelAndView listall() {
        ICommand<Integer> command = new FindAllUsersCommand<>();
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView newUserForm() {
        ICommand<Integer> command = new SaveUserFormCommand<>();
        ModelAndView mv = command.execute();
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView newUser(UsuarioDTO dto) {
        ICommand<UsuarioDTO> command = new SaveUserCommand<UsuarioDTO>();
        command.setValue(dto);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable("id") int id) {
        ICommand<Integer> command = new UpdateUserFormCommand<Integer>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView update(UsuarioUpdateDTO dto) {
        ICommand<UsuarioUpdateDTO> command = new UpdateUserCommand<UsuarioUpdateDTO>();
        command.setValue(dto);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/listbyid/")
    public ModelAndView findById(@RequestParam("id") int id) {
        ICommand<Integer> command = new ListUserByIdCommand<Integer>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ICommand<Integer> command = new DeleteUserCommand<Integer>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }
}
