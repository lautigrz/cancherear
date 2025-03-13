package com.tallerwebi.controller;

import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.service.ServicioLogin;
import com.tallerwebi.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControllerLogin {

    private ServicioLogin servicioLogin;

    @Autowired
    public ControllerLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @GetMapping(path = "/login")
    public ModelAndView vistaLogin() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("usuario", new Usuario());

        return mv;
    }

    @PostMapping(path = "/loggin")
    public ModelAndView login(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Usuario usuarioLogin = this.servicioLogin.loginUsuario(usuario.getEmail(),usuario.getPassword());

        if(usuarioLogin == null) {
            model.put("error", "Usuario o clave incorrecta");
            model.put("datosLoginDTO", new Usuario());
            return new ModelAndView("login", model);
        }

        HttpSession session = request.getSession();
        session.setAttribute("publicador", usuarioLogin);

        return new ModelAndView("redirect:/" + usuarioLogin.getRole().name().toLowerCase() + "Home");
    }

}
