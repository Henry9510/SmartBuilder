package com.garsite.SmartBuilder.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Entity
@Table(name = "sistem_ele") 
public class SistemEle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voltaje;
    private String tipoControl;
    private String tipoSensorWD;
    private String tipoSensorTemp;          
    private String tipoMedidor;
    private String impresora;
    private String modem;

    
}
