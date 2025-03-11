package com.tallerwebi.service;

import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioLoginImp implements ServicioLogin {
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioLoginImp(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario loginUsuario(String email, String password) {
        return this.repositorioUsuario.buscarUsuarioPorCredenciales(email, password);
    }

}
