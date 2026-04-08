package com.sanrio.micromarket.dto.venta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record VentaRequest(
    @NotNull Long empleadoId, @NotEmpty @Valid List<VentaItemRequest> items) {}
