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
@Table(name = "valve_control")
public class ValveControl {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String tipoConexion;
    private String numeroParte;
    private String nombre;
    private String capacidadFlujo;
    private String tipoValvula;         
    private String tipoAcionamiento;




}
