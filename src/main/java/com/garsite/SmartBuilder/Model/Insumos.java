package com.garsite.SmartBuilder.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "insumo")
public class Insumos{
 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_insumo;

    private String numero_parte;
    private String nombre;
    private String procedencia;
    private String descripcion;
    private String unidad_medida;

}

