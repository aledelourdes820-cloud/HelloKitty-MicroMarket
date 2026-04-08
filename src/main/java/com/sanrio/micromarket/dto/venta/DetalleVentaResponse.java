package com.sanrio.micromarket.dto.venta;

import java.math.BigDecimal;

public record DetalleVentaResponse(
    Long productoId, String nombreProducto, Integer cantidad, BigDecimal precioUnitario) {}
