package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/errors")
public class ErrorController {
    @GetMapping("/400")
    public ModelAndView showErrorPage400() {
        ModelAndView modelAndView = new ModelAndView("/error/400");
        return modelAndView;
    }

    @GetMapping("/403")
    public ModelAndView showErrorPage403() {
        ModelAndView modelAndView = new ModelAndView("/error/403");
        return modelAndView;
    }

    @GetMapping("/404")
    public ModelAndView showErrorPage404() {
        ModelAndView modelAndView = new ModelAndView("/error/404");
        return modelAndView;
    }

    @GetMapping("/500")
    public ModelAndView showErrorPage500() {
        ModelAndView modelAndView = new ModelAndView("/error/500");
        return modelAndView;
    }

    @GetMapping("/503")
    public ModelAndView showErrorPage503() {
        ModelAndView modelAndView = new ModelAndView("/error/503");
        return modelAndView;
    }
}
