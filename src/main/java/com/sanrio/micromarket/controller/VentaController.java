package com.sanrio.micromarket.controller;

import com.sanrio.micromarket.dto.venta.VentaRequest;
import com.sanrio.micromarket.dto.venta.VentaResponse;
import com.sanrio.micromarket.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

  private final VentaService ventaService;

  public VentaController(VentaService ventaService) {
    this.ventaService = ventaService;
  }

  @GetMapping("/{id}")
  public VentaResponse obtener(@PathVariable Long id) {
    return ventaService.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VentaResponse registrar(@Valid @RequestBody VentaRequest req) {
    return ventaService.registrar(req);
  }
}
