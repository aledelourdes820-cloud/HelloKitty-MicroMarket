package com.sanrio.micromarket.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime fecha;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "empleado_id", nullable = false)
  private Empleado empleado;

  @Column(nullable = false, precision = 14, scale = 2)
  private BigDecimal subtotal;

  @Column(nullable = false, precision = 14, scale = 2)
  private BigDecimal iva;

  @Column(nullable = false, precision = 14, scale = 2)
  private BigDecimal total;

  @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DetalleVenta> detalles = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public Empleado getEmpleado() {
    return empleado;
  }

  public void setEmpleado(Empleado empleado) {
    this.empleado = empleado;
  }

  public BigDecimal getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(BigDecimal subtotal) {
    this.subtotal = subtotal;
  }

  public BigDecimal getIva() {
    return iva;
  }

  public void setIva(BigDecimal iva) {
    this.iva = iva;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public List<DetalleVenta> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<DetalleVenta> detalles) {
    this.detalles = detalles;
  }
}
