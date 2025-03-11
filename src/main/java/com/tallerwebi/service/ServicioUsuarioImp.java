package com.tallerwebi.service;

import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioUsuarioImp implements ServicioUsuario {


    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuarioImp(RepositorioUsuario repositorioUsuario) { this.repositorioUsuario = repositorioUsuario; }



    @Override
    public void registrarUsuario(Usuario usuario) {
        this.repositorioUsuario.guardarUsuario(usuario);
    }
}
