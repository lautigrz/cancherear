package com.tallerwebi.excepciones;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String manejoDeValidacionFormulario(IllegalArgumentException ex,RedirectAttributes redirectAttributes) {
        // Retornar un mensaje de error con un estado HTTP 400 (Bad Request)
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/registro";
    }

    @ExceptionHandler(value = UsuarioException.class)
    public String manejarUsuarioException(UsuarioException e, RedirectAttributes redirectAttributes) {
        // Agregar el mensaje de error a los atributos para que sea mostrado en la vista
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        // Redirigir a la página de login
        return "redirect:/login";
    }
    @ExceptionHandler(value = RegistroException.class)
    public String manejarRegistroException(RegistroException e, RedirectAttributes redirectAttributes) {
        // Agregar el mensaje de error a los atributos para que sea mostrado en la vista
        redirectAttributes.addFlashAttribute("error", e.getMessage());

        return "redirect:/registro";
    }
}
