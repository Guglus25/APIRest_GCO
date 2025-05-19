package com.apirestgco.apirest_gco.Models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idProducto;
    private String tipo;
    private Integer cantidad;
    private Date fecha;
    private String descripcion;
}
