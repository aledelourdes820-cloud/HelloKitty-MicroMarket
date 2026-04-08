package com.sanrio.micromarket.dto.producto;

import java.math.BigDecimal;

public record ProductoResponse(
    Long id,
    String nombre,
    String codigoBarras,
    BigDecimal precio,
    Integer stock,
    boolean activo,
    Long categoriaId,
    String categoriaNombre) {}
