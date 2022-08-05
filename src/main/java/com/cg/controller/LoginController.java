package com.cg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/")
    public String showHome() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("/login");
    }
}
