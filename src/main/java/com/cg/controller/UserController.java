package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public ModelAndView showProductList() {
        ModelAndView modelAndView = new ModelAndView("/user/list");
        return modelAndView;
    }
}
