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
@Table(name = "venturi")
public class Venturi {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_venturi;

    private String numero_parte;
    private String nombre;




}
