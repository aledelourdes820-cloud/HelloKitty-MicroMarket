package com.sanrio.micromarket.controller;

import com.sanrio.micromarket.dto.producto.ProductoRequest;
import com.sanrio.micromarket.dto.producto.ProductoResponse;
import com.sanrio.micromarket.service.ProductoService;
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
@RequestMapping("/api/productos")
public class ProductoController {

  private final ProductoService productoService;

  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping
  public List<ProductoResponse> listar() {
    return productoService.listarActivos();
  }

  @GetMapping("/{id}")
  public ProductoResponse obtener(@PathVariable Long id) {
    return productoService.obtener(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductoResponse crear(@Valid @RequestBody ProductoRequest req) {
    return productoService.crear(req);
  }

  @PutMapping("/{id}")
  public ProductoResponse actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest req) {
    return productoService.actualizar(id, req);
  }

  /** Borrado lógico */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    productoService.eliminar(id);
  }
}
