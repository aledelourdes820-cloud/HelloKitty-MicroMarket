package com.sanrio.micromarket.controller;

import com.sanrio.micromarket.dto.empleado.EmpleadoRequest;
import com.sanrio.micromarket.dto.empleado.EmpleadoResponse;
import com.sanrio.micromarket.model.enums.Cargo;
import com.sanrio.micromarket.service.EmpleadoService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

  private final EmpleadoService empleadoService;

  public EmpleadoController(EmpleadoService empleadoService) {
    this.empleadoService = empleadoService;
  }

  @GetMapping
  public List<EmpleadoResponse> listar(
      @RequestParam(required = false) Cargo cargo,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
    return empleadoService.listar(cargo, fechaInicio, fechaFin);
  }

  @GetMapping("/{id}")
  public EmpleadoResponse obtener(@PathVariable Long id) {
    return empleadoService.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EmpleadoResponse crear(@Valid @RequestBody EmpleadoRequest req) {
    return empleadoService.crear(req);
  }

  @PutMapping("/{id}")
  public EmpleadoResponse actualizar(@PathVariable Long id, @Valid @RequestBody EmpleadoRequest req) {
    return empleadoService.actualizar(id, req);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    empleadoService.eliminar(id);
  }
}
