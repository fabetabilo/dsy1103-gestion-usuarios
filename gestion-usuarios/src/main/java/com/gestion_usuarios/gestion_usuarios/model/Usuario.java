package com.gestion_usuarios.gestion_usuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;                          // idUsuario para ser identificado dentro del sistema

    @Column(length = 10, nullable = false)
    private String runUsr;

    @Column(length = 50, nullable = false)
    private String pnombreUsr;

    @Column(length = 50, nullable = true)
    private String snombreUsr;

    @Column(length = 70, nullable = false)
    private String apellidosUsr;

    @Column(nullable = false, unique = true)        // telefono debe ser unico
    private int telefonoUsr;

    @Column(length = 100, nullable = false, unique = true)
    private String correoUsr;

    @Column(length = 100, nullable = false)
    private String direccionUsr;

    // "cod_cargo" es el nombre de la columna en la base de datos, "codCargo" es el atributo en el modelo java Cargo
    @ManyToOne
    @JoinColumn(name = "cod_cargo", referencedColumnName = "codCargo", nullable = false)
    private Cargo cargo;



}
