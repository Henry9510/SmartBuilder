package com.garsite.SmartBuilder.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String modelo;
    private String caudal;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "chasis")
    private Chasis chasis;

    @ManyToOne
    @JoinColumn(name = "sistema_abaste")
    private SistemaAbaste sistemaAbaste;

    @ManyToOne
    @JoinColumn(name = "sistema_ele")
    private SistemEle sistemaElectrico;

    @ManyToOne
    @JoinColumn(name = "control_panel")
    private ControlPanel controlPanel;

    @ManyToOne
    @JoinColumn(name = "hoses")
    private Hose hose;

    @ManyToOne
    @JoinColumn(name = "pit_couplers")
    private PitCoupler pitCoupler;

    @ManyToOne
    @JoinColumn(name = "recovery_tanks")
    private RecoveryTank recoveryTank;

    @ManyToOne
    @JoinColumn(name = "hose_reels")
    private Carretel carretel;

    @ManyToOne
    @JoinColumn(name = "filtros")
    private Filtros filtros;

    @ManyToOne
    @JoinColumn(name = "valve_control")
    private ValveControl valveControl;

}
