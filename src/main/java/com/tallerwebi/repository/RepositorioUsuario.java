package com.tallerwebi.repository;

import com.tallerwebi.dominio.Usuario;

public interface RepositorioUsuario {
    void guardarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorCredenciales(String email, String password);
    Boolean existeUsuario(String email);
}
