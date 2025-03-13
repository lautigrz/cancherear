package com.tallerwebi.service;

import com.tallerwebi.dominio.Cancha;
import com.tallerwebi.repository.RepositorioCancha;
import com.tallerwebi.repository.RepositorioCanchaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCanchaImp implements ServicioCancha {
    private RepositorioCancha repositorioCancha;


    @Autowired
    public ServicioCanchaImp(RepositorioCancha repositorioCancha) {
        this.repositorioCancha = repositorioCancha;
    }

    @Override
    public void guardarCancha(Cancha cancha) {
        repositorioCancha.guardarCancha(cancha);
    }

    @Override
    public List<Cancha> listarCanchasDePublicador(Long idPublicador) {
        return this.repositorioCancha.listarCanchasDePublicador(idPublicador);
    }
}
