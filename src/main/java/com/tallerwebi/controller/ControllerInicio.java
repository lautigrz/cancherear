package com.tallerwebi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerInicio {

    @GetMapping(path = "/inicio")
    public ModelAndView inicio() {
        return new ModelAndView("inicio");
    }

}
