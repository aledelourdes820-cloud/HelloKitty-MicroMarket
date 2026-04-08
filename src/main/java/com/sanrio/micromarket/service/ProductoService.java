package com.sanrio.micromarket.service;

import com.sanrio.micromarket.dto.producto.ProductoRequest;
import com.sanrio.micromarket.dto.producto.ProductoResponse;
import com.sanrio.micromarket.exception.BadRequestException;
import com.sanrio.micromarket.exception.NotFoundException;
import com.sanrio.micromarket.model.entity.Categoria;
import com.sanrio.micromarket.model.entity.Producto;
import com.sanrio.micromarket.repository.CategoriaRepository;
import com.sanrio.micromarket.repository.ProductoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

  private final ProductoRepository productoRepository;
  private final CategoriaRepository categoriaRepository;

  public ProductoService(
      ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
    this.productoRepository = productoRepository;
    this.categoriaRepository = categoriaRepository;
  }

  @Transactional(readOnly = true)
  public List<ProductoResponse> listarActivos() {
    return productoRepository.findByActivoTrue().stream().map(this::toResponse).toList();
  }

  @Transactional(readOnly = true)
  public ProductoResponse obtener(Long id) {
    Producto p =
        productoRepository
            .findByIdAndActivoTrue(id)
            .orElseThrow(() -> new NotFoundException("Producto no encontrado o inactivo: " + id));
    return toResponse(p);
  }

  @Transactional
  public ProductoResponse crear(ProductoRequest req) {
    if (productoRepository.existsByCodigoBarras(req.codigoBarras())) {
      throw new BadRequestException("Ya existe un producto con el código de barras indicado");
    }
    Categoria cat =
        categoriaRepository
            .findById(req.categoriaId())
            .orElseThrow(
                () -> new NotFoundException("Categoría no encontrada: " + req.categoriaId()));
    Producto p = new Producto();
    p.setNombre(req.nombre());
    p.setCodigoBarras(req.codigoBarras());
    p.setPrecio(req.precio());
    p.setStock(req.stock());
    p.setActivo(true);
    p.setCategoria(cat);
    p = productoRepository.save(p);
    return toResponse(p);
  }

  @Transactional
  public ProductoResponse actualizar(Long id, ProductoRequest req) {
    Producto p =
        productoRepository
            .findByIdAndActivoTrue(id)
            .orElseThrow(() -> new NotFoundException("Producto no encontrado o inactivo: " + id));
    if (productoRepository.existsByCodigoBarrasAndIdNot(req.codigoBarras(), id)) {
      throw new BadRequestException("Ya existe otro producto con el código de barras indicado");
    }
    Categoria cat =
        categoriaRepository
            .findById(req.categoriaId())
            .orElseThrow(
                () -> new NotFoundException("Categoría no encontrada: " + req.categoriaId()));
    p.setNombre(req.nombre());
    p.setCodigoBarras(req.codigoBarras());
    p.setPrecio(req.precio());
    p.setStock(req.stock());
    p.setCategoria(cat);
    p = productoRepository.save(p);
    return toResponse(p);
  }

  /** Borrado lógico */
  @Transactional
  public void eliminar(Long id) {
    Producto p =
        productoRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + id));
    if (!p.isActivo()) {
      return;
    }
    p.setActivo(false);
    productoRepository.save(p);
  }

  private ProductoResponse toResponse(Producto p) {
    return new ProductoResponse(
        p.getId(),
        p.getNombre(),
        p.getCodigoBarras(),
        p.getPrecio(),
        p.getStock(),
        p.isActivo(),
        p.getCategoria().getId(),
        p.getCategoria().getNombre());
  }
}
