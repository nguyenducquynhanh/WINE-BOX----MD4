package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public ModelAndView showListProduct() {
        ModelAndView modelAndView = new ModelAndView("/admin/list");
        return modelAndView;
    }


    @GetMapping("/list_user")
    public ModelAndView showListUser() {
        ModelAndView modelAndView = new ModelAndView("/admin/listUser");
        return modelAndView;
    }
}
