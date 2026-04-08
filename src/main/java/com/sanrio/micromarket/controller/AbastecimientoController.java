package com.sanrio.micromarket.controller;

import com.sanrio.micromarket.dto.proveedor.EntradaAlmacenRequest;
import com.sanrio.micromarket.dto.proveedor.EntradaAlmacenResponse;
import com.sanrio.micromarket.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/abastecimiento")
public class AbastecimientoController {

  private final ProveedorService proveedorService;

  public AbastecimientoController(ProveedorService proveedorService) {
    this.proveedorService = proveedorService;
  }

  /** Entrada de almacén — suma stock y relaciona producto–proveedor */
  @PostMapping("/entrada")
  public EntradaAlmacenResponse entrada(@Valid @RequestBody EntradaAlmacenRequest req) {
    return proveedorService.entradaAlmacen(req);
  }
}
