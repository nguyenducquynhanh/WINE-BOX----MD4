package com.cg.controller;

import com.cg.model.dto.UserDTO;
import com.cg.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private IUserService userService;

    @GetMapping("/users/{id}")
    public ModelAndView showDashboardUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/homeUser");
        Optional<UserDTO> userDTO = userService.findUserDTOByID(id);
        modelAndView.addObject("user",userDTO.get());
        return modelAndView;
    }

    @GetMapping("/admins/{id}")
    public ModelAndView showDashboardAdmin(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/home");
        Optional<UserDTO> userDTO = userService.findUserDTOByID(id);
        modelAndView.addObject("user",userDTO.get());
        return modelAndView;
    }

}
