package com.tallerwebi.repository;

import com.tallerwebi.dominio.Cancha;

import java.util.List;

public interface RepositorioCancha {
    void guardarCancha(Cancha cancha);
    List<Cancha> listarCanchasDePublicador(Long idPublicador);
    Boolean cambiarEstadoDeCancha(Long idCancha);

}
