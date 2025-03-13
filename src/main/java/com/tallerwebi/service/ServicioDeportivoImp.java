package com.tallerwebi.service;

import com.tallerwebi.dominio.Deportivo;
import com.tallerwebi.repository.RepositorioDeportivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ServicioDeportivoImp implements ServicioDeportivo {

    private RepositorioDeportivo repositorioDeportivo;
    @Autowired

    public ServicioDeportivoImp(RepositorioDeportivo repositorioDeportivo) {
        this.repositorioDeportivo = repositorioDeportivo;
    }

    @Override
    public void guardarDeportivo(Deportivo deportivo, MultipartFile imagen) throws IOException {

        String url = guardarImagen(imagen);
        this.repositorioDeportivo.guardar(deportivo);
    }

    @Override
    public String guardarImagen(MultipartFile imagen) throws IOException {
        // Definir la carpeta donde se guardarán las imágenes
        String carpetaDestino = "src/main/resources/static/images/";

        // Obtener el nombre de la imagen y generar un nombre único
        String nombreArchivo = System.currentTimeMillis() + "-" + imagen.getOriginalFilename();

        // Crear un archivo de destino en la carpeta
        File archivoDestino = new File(carpetaDestino + nombreArchivo);

        // Guardar la imagen en el sistema de archivos
        imagen.transferTo(archivoDestino);

        // Retornar la URL de la imagen guardada
        return "/images/" + nombreArchivo;  // Asegúrate de que esta ruta sea accesible desde tu servidor
    }

}
