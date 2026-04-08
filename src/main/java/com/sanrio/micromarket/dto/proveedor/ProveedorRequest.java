package com.sanrio.micromarket.dto.proveedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProveedorRequest(
    @NotBlank @Size(max = 200) String nombre,
    @NotBlank @Size(max = 32) String nit,
    @Size(max = 40) String telefono) {}
