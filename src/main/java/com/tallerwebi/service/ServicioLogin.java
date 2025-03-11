package com.tallerwebi.service;

import com.tallerwebi.dominio.Usuario;

public interface ServicioLogin {

    Usuario loginUsuario(String email, String password);
}
