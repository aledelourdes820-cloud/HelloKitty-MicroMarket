package com.sanrio.micromarket.model.entity;

import com.sanrio.micromarket.model.enums.Cargo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 20)
  private String cedula;

  @Column(nullable = false, length = 150)
  private String nombre;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private Cargo cargo;

  @Column(nullable = false, name = "fecha_ingreso")
  private LocalDate fechaIngreso;

  @Column(nullable = false, precision = 12, scale = 2)
  private BigDecimal salario;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCedula() {
    return cedula;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public LocalDate getFechaIngreso() {
    return fechaIngreso;
  }

  public void setFechaIngreso(LocalDate fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }
}
