package com.sanrio.micromarket.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequest(
    @NotBlank @Size(max = 120) String nombre,
    @Size(max = 500) String descripcion) {}
