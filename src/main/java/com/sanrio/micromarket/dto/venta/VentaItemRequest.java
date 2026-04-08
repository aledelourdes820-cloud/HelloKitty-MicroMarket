package com.sanrio.micromarket.dto.venta;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VentaItemRequest(@NotNull Long productoId, @NotNull @Min(1) Integer cantidad) {}
