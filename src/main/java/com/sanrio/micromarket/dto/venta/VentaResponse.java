package com.sanrio.micromarket.dto.venta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponse(
    Long id,
    LocalDateTime fecha,
    Long empleadoId,
    String nombreEmpleado,
    BigDecimal subtotal,
    BigDecimal iva,
    BigDecimal total,
    List<DetalleVentaResponse> detalles) {}
