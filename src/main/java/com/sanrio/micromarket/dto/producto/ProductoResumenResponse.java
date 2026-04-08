package com.sanrio.micromarket.dto.producto;

import java.math.BigDecimal;

public record ProductoResumenResponse(
    Long id, String nombre, String codigoBarras, BigDecimal precio, Integer stock) {}
