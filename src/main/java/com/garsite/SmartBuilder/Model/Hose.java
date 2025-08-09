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
@Table(name = "hoses")
public class Hose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_reel;
    private String material;
    private String tipoManguera;
    private String diametro;
    private String longitud;
    private String conexion;

}
