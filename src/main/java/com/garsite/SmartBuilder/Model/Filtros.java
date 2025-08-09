package com.garsite.SmartBuilder.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "filtros")
public class Filtros {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoFiltro;

    private String caudal;
    private String tipoFiltro;
    private String tipoMaterial;    
    private String tipoMontaje;
    private String tipoConexiones;
    private String numeroParte;
    private String nombre;
    private String diferenciaPresion;
    

}
