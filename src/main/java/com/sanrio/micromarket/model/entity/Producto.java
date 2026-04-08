package com.sanrio.micromarket.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 200)
  private String nombre;

  @Column(nullable = false, unique = true, length = 64)
  private String codigoBarras;

  @Column(nullable = false, precision = 12, scale = 2)
  private BigDecimal precio;

  @Column(nullable = false)
  private Integer stock;

  /** Borrado lógico: false = eliminado */
  @Column(nullable = false)
  private boolean activo = true;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "categoria_id", nullable = false)
  private Categoria categoria;

  @ManyToMany
  @JoinTable(
      name = "producto_proveedor",
      joinColumns = @JoinColumn(name = "producto_id"),
      inverseJoinColumns = @JoinColumn(name = "proveedor_id"))
  private Set<Proveedor> proveedores = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCodigoBarras() {
    return codigoBarras;
  }

  public void setCodigoBarras(String codigoBarras) {
    this.codigoBarras = codigoBarras;
  }

  public BigDecimal getPrecio() {
    return precio;
  }

  public void setPrecio(BigDecimal precio) {
    this.precio = precio;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Set<Proveedor> getProveedores() {
    return proveedores;
  }

  public void setProveedores(Set<Proveedor> proveedores) {
    this.proveedores = proveedores;
  }
}
