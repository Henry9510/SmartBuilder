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
@Table(name = "control_panel")
public class ControlPanel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_reel;

    private String material;
    private String normativa;
    private String diferencialPresion;          
    private String registradora;
    private String luces;
     
}
