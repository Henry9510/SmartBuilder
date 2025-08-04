package com.garsite.SmartBuilder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class item {
 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_insumo;
    private String numero_parte;
    private String nombre;
    private String procedencia;
    private String descripcion;
    private String unidad_medida;

}

