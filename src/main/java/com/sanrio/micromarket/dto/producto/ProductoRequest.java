package com.sanrio.micromarket.dto.producto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductoRequest(
    @NotBlank @Size(max = 200) String nombre,
    @NotBlank @Size(max = 64) String codigoBarras,
    @NotNull @DecimalMin("0.01") BigDecimal precio,
    @NotNull @Min(0) Integer stock,
    @NotNull Long categoriaId) {}
