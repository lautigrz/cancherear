package com.tallerwebi.controller;

import com.tallerwebi.dominio.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControllerPublicador {

    @GetMapping(path = "/publicadorHome")
    public ModelAndView publicador(HttpSession session) {

        ModelMap model = new ModelMap();
        Usuario usuario = (Usuario) session.getAttribute("publicador");

        if(usuario == null) {
            model.put("publicador","usuario");
        }

        model.put("publicador",usuario);

        ModelAndView mv = new ModelAndView("publicadorHome", model);
        return mv;
    }

    @GetMapping(path = "/nuevaCancha")
    public ModelAndView addCancha() {
        ModelAndView mv = new ModelAndView("nuevaCancha");
        return mv;
    }

}
