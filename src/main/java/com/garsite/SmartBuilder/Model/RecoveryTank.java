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
@Table(name = "recovery_tanks")
public class RecoveryTank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String material;
    private String dimensiones;
    private String capacidadCarga;
    private String sensoresNivel;
    private String bombaVaciado;

}

