package com.tallerwebi.service;

import com.tallerwebi.dominio.Usuario;

public interface ServicioUsuario {
    void registrarUsuario(Usuario usuario);
    Usuario loginUsuario(String email, String password);
    Boolean existeUsuario(String email);
}
