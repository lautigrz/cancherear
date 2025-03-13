package com.tallerwebi.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String superficie;
    @Column(nullable = false)
    private String canchaDe;

    //@Column(nullable = false)
    //private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "usuario_id") // Clave foránea
    private Usuario usuario;

    //@ManyToOne
    //@JoinColumn(name = "deportivo_id")
    //private Deportivo deportivo;

}
