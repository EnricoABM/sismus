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
import com.duviri.sismus.controller.command.trip.DeleteTripCommand;
import com.duviri.sismus.controller.command.trip.ListTripByIdCommand;
import com.duviri.sismus.controller.command.trip.ListTripByUserCommand;
import com.duviri.sismus.controller.command.trip.SaveTripCommand;
import com.duviri.sismus.controller.command.trip.SaveTripFormCommand;
import com.duviri.sismus.controller.command.trip.UpdateTripCommand;
import com.duviri.sismus.controller.command.trip.UpdateTripFormCommand;
import com.duviri.sismus.dto.ViagemDTO;
import com.duviri.sismus.dto.ViagemUpdateDTO;

@Controller
@RequestMapping("/trip")
public class ViagemController {


    @GetMapping("/listbyuser/{id}")
    public ModelAndView listAll(@PathVariable("id") int id) {
        ICommand<Integer> command = new ListTripByUserCommand<>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/create/{id}")
    public ModelAndView newTripForm(@PathVariable("id") int id) {
        ICommand<Integer> command = new SaveTripFormCommand<>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView newTrip(ViagemDTO dto) {
        ICommand<ViagemDTO> command = new SaveTripCommand<>();
        command.setValue(dto);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable("id") int id) {
        ICommand<Integer> command = new UpdateTripFormCommand<>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView update(ViagemUpdateDTO dto) {
        ICommand<ViagemUpdateDTO> command = new UpdateTripCommand<>();
        command.setValue(dto);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/listbyid/")
    public ModelAndView findById(@RequestParam("id") int id, @RequestParam("criador") int criadorId) {
        IListByIdCommand<Integer> command = new ListTripByIdCommand<>();
        command.setId(id);
        command.setValue(criadorId);
        ModelAndView mv = command.execute();
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ICommand<Integer> command = new DeleteTripCommand<>();
        command.setValue(id);
        ModelAndView mv = command.execute();
        return mv;
    }
}
