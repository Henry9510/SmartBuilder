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
@Table(name = "sistema_abaste")
public class SistemaAbaste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String MaterialTuberia ;
    private String dimenciones;
    private String MaterialModulo;
    private String LineasSense;
    private String eductor;
    private String supresor;
    private String acumulador;
    private String tipoValvula; 

}
