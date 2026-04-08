package com.sanrio.micromarket.service;

import com.sanrio.micromarket.dto.categoria.CategoriaRequest;
import com.sanrio.micromarket.dto.categoria.CategoriaResponse;
import com.sanrio.micromarket.dto.producto.ProductoResumenResponse;
import com.sanrio.micromarket.exception.NotFoundException;
import com.sanrio.micromarket.model.entity.Categoria;
import com.sanrio.micromarket.repository.CategoriaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @Transactional(readOnly = true)
  public List<CategoriaResponse> listarSinProductos() {
    return categoriaRepository.findAll().stream()
        .map(c -> new CategoriaResponse(c.getId(), c.getNombre(), c.getDescripcion(), List.of()))
        .toList();
  }

  @Transactional(readOnly = true)
  public CategoriaResponse obtenerConProductos(Long id) {
    Categoria c =
        categoriaRepository
            .findByIdConProductos(id)
            .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + id));
    List<ProductoResumenResponse> productos =
        c.getProductos().stream()
            .filter(p -> p.isActivo())
            .map(
                p ->
                    new ProductoResumenResponse(
                        p.getId(), p.getNombre(), p.getCodigoBarras(), p.getPrecio(), p.getStock()))
            .collect(Collectors.toList());
    return new CategoriaResponse(c.getId(), c.getNombre(), c.getDescripcion(), productos);
  }

  @Transactional
  public CategoriaResponse crear(CategoriaRequest req) {
    Categoria c = new Categoria();
    c.setNombre(req.nombre());
    c.setDescripcion(req.descripcion());
    c = categoriaRepository.save(c);
    return new CategoriaResponse(c.getId(), c.getNombre(), c.getDescripcion(), List.of());
  }

  @Transactional
  public CategoriaResponse actualizar(Long id, CategoriaRequest req) {
    Categoria c =
        categoriaRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + id));
    c.setNombre(req.nombre());
    c.setDescripcion(req.descripcion());
    categoriaRepository.save(c);
    return obtenerConProductos(id);
  }

  @Transactional
  public void eliminar(Long id) {
    if (!categoriaRepository.existsById(id)) {
      throw new NotFoundException("Categoría no encontrada: " + id);
    }
    categoriaRepository.deleteById(id);
  }
}
