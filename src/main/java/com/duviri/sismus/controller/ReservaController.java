package com.duviri.sismus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.duviri.sismus.controller.command.ICommand;
import com.duviri.sismus.controller.command.IListByIdCommand;
import com.duviri.sismus.controller.command.reservation.CancelReservationCommand;
import com.duviri.sismus.controller.command.reservation.ListReservationByIdCommand;
import com.duviri.sismus.controller.command.reservation.ListReservationByUserCommand;
import com.duviri.sismus.controller.command.reservation.SaveReservationCommand;
import com.duviri.sismus.controller.command.reservation.SaveReservationFormCommand;
import com.duviri.sismus.dto.ReservaDTO;

@Controller
@RequestMapping("/reservation")
public class ReservaController {

    @GetMapping("/listbyuser/{id}")
    public ModelAndView listByUser(@PathVariable("id") int id) {
        ICommand<Integer> command = new ListReservationByUserCommand<>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/create/{id}")
    public ModelAndView create(@PathVariable("id") int id) {
        ICommand<Integer> command = new SaveReservationFormCommand<>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView create(ReservaDTO dto) {
        ICommand<ReservaDTO> command = new SaveReservationCommand<>();
        command.setValue(dto);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/cancel/{id}")
    public ModelAndView cancel(@PathVariable("id") int id) {
        ICommand<Integer> command = new CancelReservationCommand<>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/listbyid/")
    public ModelAndView findById(@RequestParam("id") int id, @RequestParam("usuario") int usuario) {
        IListByIdCommand<Integer> command = new ListReservationByIdCommand<>();
        command.setValue(usuario);
        command.setId(id);
        ModelAndView mv = command.execute();
        return mv;
    }

}
