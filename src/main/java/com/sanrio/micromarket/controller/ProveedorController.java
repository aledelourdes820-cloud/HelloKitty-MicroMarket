package com.sanrio.micromarket.controller;

import com.sanrio.micromarket.dto.proveedor.ProveedorRequest;
import com.sanrio.micromarket.dto.proveedor.ProveedorResponse;
import com.sanrio.micromarket.service.ProveedorService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

  private final ProveedorService proveedorService;

  public ProveedorController(ProveedorService proveedorService) {
    this.proveedorService = proveedorService;
  }

  @GetMapping
  public List<ProveedorResponse> listar() {
    return proveedorService.listar();
  }

  @GetMapping("/{id}")
  public ProveedorResponse obtener(@PathVariable Long id) {
    return proveedorService.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProveedorResponse crear(@Valid @RequestBody ProveedorRequest req) {
    return proveedorService.crear(req);
  }

  @PutMapping("/{id}")
  public ProveedorResponse actualizar(
      @PathVariable Long id, @Valid @RequestBody ProveedorRequest req) {
    return proveedorService.actualizar(id, req);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    proveedorService.eliminar(id);
  }
}
