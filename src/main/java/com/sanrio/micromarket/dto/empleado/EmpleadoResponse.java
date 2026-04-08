package com.sanrio.micromarket.dto.empleado;

import com.sanrio.micromarket.model.enums.Cargo;
import java.math.BigDecimal;
import java.time.LocalDate;

public record EmpleadoResponse(
    Long id,
    String cedula,
    String nombre,
    Cargo cargo,
    LocalDate fechaIngreso,
    BigDecimal salario) {}
