package com.sanrio.micromarket.controller;

import com.sanrio.micromarket.dto.categoria.CategoriaRequest;
import com.sanrio.micromarket.dto.categoria.CategoriaResponse;
import com.sanrio.micromarket.service.CategoriaService;
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
@RequestMapping("/api/categorias")
public class CategoriaController {

  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @GetMapping
  public List<CategoriaResponse> listar() {
    return categoriaService.listarSinProductos();
  }

  @GetMapping("/{id}")
  public CategoriaResponse obtenerConProductos(@PathVariable Long id) {
    return categoriaService.obtenerConProductos(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoriaResponse crear(@Valid @RequestBody CategoriaRequest req) {
    return categoriaService.crear(req);
  }

  @PutMapping("/{id}")
  public CategoriaResponse actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequest req) {
    return categoriaService.actualizar(id, req);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    categoriaService.eliminar(id);
  }
}
