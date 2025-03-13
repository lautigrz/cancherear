package com.tallerwebi.service;

import com.tallerwebi.dominio.Deportivo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ServicioDeportivo {
    void guardarDeportivo(Deportivo deportivo, MultipartFile imagen)  throws IOException;
    String guardarImagen(MultipartFile imagen) throws IOException;
}
