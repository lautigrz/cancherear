package com.tallerwebi.controller;

import com.tallerwebi.dominio.Rol;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.excepciones.RegistroException;
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

        if (this.servicioUsuario.existeUsuario(usuario.getEmail())) {
            throw new RegistroException("Correo electrónico ya registrado");
        }
        Rol rolObj;
        try {
            // Intentamos convertir la cadena a un valor del enum, sin asignar valor por defecto de antemano
            rolObj = Rol.valueOf(this.rol.toUpperCase());
        } catch (NullPointerException e) {
            // Si el valor no corresponde a un valor válido del enum, asignamos un rol por defecto
            rolObj = Rol.PUBLICADOR; // Asignación de valor por defecto
        }

        usuario.setRole(rolObj); // Asignamos el rol al usuario
        this.servicioUsuario.registrarUsuario(usuario); // Registramos el usuario

        return new ModelAndView("redirect:/registro");
    }
}
