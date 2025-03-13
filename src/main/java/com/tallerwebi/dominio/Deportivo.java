package com.tallerwebi.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Deportivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDeportivo;

    private String nombreLugar;

    private String descripcionLugar;

    private String ubicacionLugar;

    private String numeroTelefono;

    private String emailContacto;

   // @ManyToOne
   // @JoinColumn(name = "usuario_id")
   // private Usuario usuarioDepo;

    //@OneToMany(mappedBy = "deportivo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Cancha> canchas = new ArrayList<Cancha>();
}
