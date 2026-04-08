package com.sanrio.micromarket.dto.empleado;

import com.sanrio.micromarket.model.enums.Cargo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record EmpleadoRequest(
    @NotBlank @Size(max = 20) String cedula,
    @NotBlank @Size(max = 150) String nombre,
    @NotNull Cargo cargo,
    @NotNull LocalDate fechaIngreso,
    @NotNull @DecimalMin("0.0") BigDecimal salario) {}
