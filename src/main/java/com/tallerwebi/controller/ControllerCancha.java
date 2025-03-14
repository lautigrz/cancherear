package com.tallerwebi.controller;

import com.tallerwebi.dominio.Cancha;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.service.ServicioCancha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ControllerCancha {

    private ServicioCancha servicioCancha;

    @Autowired
    public ControllerCancha(ServicioCancha servicioCancha) {
        this.servicioCancha = servicioCancha;

    }

    @GetMapping(path = "nueva-cancha")
    public ModelAndView anadirCancha(HttpSession session) {
        ModelMap model = new ModelMap();
        Usuario usuario = (Usuario) session.getAttribute("publicador");

        if (usuario != null) {
            model.put("usuario", usuario);
            model.put("canchas", this.servicioCancha.listarCanchasDePublicador(usuario.getId()));
        }

        // Agregar objeto Cancha para que se use en el formulario
        model.put("cancha", new Cancha());

        return new ModelAndView("nuevaCancha", model);
    }


    @PostMapping(path = "/guardar")
   public ModelAndView guardarCancha(@ModelAttribute("cancha") Cancha cancha, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Usuario usuario = (Usuario) session.getAttribute("publicador");
            cancha.setUsuario(usuario);
            cancha.setEstado(true);
            this.servicioCancha.guardarCancha(cancha);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ModelAndView("redirect:nueva-cancha");
    }

    @GetMapping(path = "/cambiar/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> cambiarEstado(@PathVariable Long id) {

        Map<String, String> response = new HashMap<>();

        try {
            Boolean estadoCambiado =  this.servicioCancha.cambiarEstadoDeCancha(id);  // Llamada al servicio

            response.put("estado", estadoCambiado.toString());
            return ResponseEntity.ok(response);  // Devuelve HTTP 200 con el nuevo estado
        } catch (Exception e) {
            response.put("estado", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // Error 500 si algo falla
        }

    }

}
