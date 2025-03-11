package com.tallerwebi.controller;

import com.tallerwebi.dominio.Rol;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.service.ServicioUsuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerRegistro {
    private ServicioUsuario servicioUsuario;
    private String rol = "";
    public ControllerRegistro(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }


    @GetMapping(path = "/registro")
    public ModelAndView vistaRegistro(@RequestParam(value = "tipo", required = false) String tipo) {

        this.rol = tipo;
        return new ModelAndView("registro").addObject("usuario", new Usuario());
    }

    @PostMapping(path = "/registrarme")
    public ModelAndView registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {

        Rol rolObj = null;
        if (!this.rol.isEmpty()) {
            try {
                rolObj = Rol.valueOf(this.rol.toUpperCase()); // Convertimos la cadena a enum
            } catch (IllegalArgumentException e) {
                // Si el valor de tipo no corresponde a ningún valor del enum, asignamos un valor por defecto
                rolObj = Rol.PUBLICADOR; // Puedes asignar el rol por defecto que prefieras
            }
        }
        usuario.setRole(rolObj);
        this.servicioUsuario.registrarUsuario(usuario);
        return new ModelAndView("redirect:/registro");
    }
}
