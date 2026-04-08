package com.sanrio.micromarket.dto.proveedor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EntradaAlmacenRequest(
    @NotNull Long productoId,
    @NotNull Long proveedorId,
    @NotNull @Min(1) Integer cantidad) {}
