package com.tallerwebi.service;

import com.tallerwebi.dominio.Cancha;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ServicioCancha {
    void guardarCancha(Cancha cancha);
    List<Cancha> listarCanchasDePublicador(Long idPublicador);
}
