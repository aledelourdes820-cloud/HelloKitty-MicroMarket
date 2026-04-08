package com.sanrio.micromarket.service;

import com.sanrio.micromarket.dto.proveedor.EntradaAlmacenRequest;
import com.sanrio.micromarket.dto.proveedor.EntradaAlmacenResponse;
import com.sanrio.micromarket.dto.proveedor.ProveedorRequest;
import com.sanrio.micromarket.dto.proveedor.ProveedorResponse;
import com.sanrio.micromarket.exception.BadRequestException;
import com.sanrio.micromarket.exception.NotFoundException;
import com.sanrio.micromarket.model.entity.Producto;
import com.sanrio.micromarket.model.entity.Proveedor;
import com.sanrio.micromarket.repository.ProductoRepository;
import com.sanrio.micromarket.repository.ProveedorRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {

  private final ProveedorRepository proveedorRepository;
  private final ProductoRepository productoRepository;

  public ProveedorService(
      ProveedorRepository proveedorRepository, ProductoRepository productoRepository) {
    this.proveedorRepository = proveedorRepository;
    this.productoRepository = productoRepository;
  }

  @Transactional(readOnly = true)
  public List<ProveedorResponse> listar() {
    return proveedorRepository.findAll().stream().map(this::toResponse).toList();
  }

  @Transactional(readOnly = true)
  public ProveedorResponse obtener(Long id) {
    Proveedor pr =
        proveedorRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Proveedor no encontrado: " + id));
    return toResponse(pr);
  }

  @Transactional
  public ProveedorResponse crear(ProveedorRequest req) {
    if (proveedorRepository.existsByNit(req.nit())) {
      throw new BadRequestException("Ya existe un proveedor con el NIT indicado");
    }
    Proveedor p = new Proveedor();
    p.setNombre(req.nombre());
    p.setNit(req.nit());
    p.setTelefono(req.telefono());
    p = proveedorRepository.save(p);
    return toResponse(p);
  }

  @Transactional
  public ProveedorResponse actualizar(Long id, ProveedorRequest req) {
    Proveedor p =
        proveedorRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Proveedor no encontrado: " + id));
    if (proveedorRepository.existsByNitAndIdNot(req.nit(), id)) {
      throw new BadRequestException("Ya existe otro proveedor con el NIT indicado");
    }
    p.setNombre(req.nombre());
    p.setNit(req.nit());
    p.setTelefono(req.telefono());
    p = proveedorRepository.save(p);
    return toResponse(p);
  }

  @Transactional
  public void eliminar(Long id) {
    if (!proveedorRepository.existsById(id)) {
      throw new NotFoundException("Proveedor no encontrado: " + id);
    }
    proveedorRepository.deleteById(id);
  }

  /** Entrada de almacén: asocia proveedor–producto y suma stock */
  @Transactional
  public EntradaAlmacenResponse entradaAlmacen(EntradaAlmacenRequest req) {
    Producto producto =
        productoRepository
            .findByIdAndActivoTrue(req.productoId())
            .orElseThrow(
                () -> new NotFoundException("Producto no encontrado o inactivo: " + req.productoId()));
    Proveedor proveedor =
        proveedorRepository
            .findById(req.proveedorId())
            .orElseThrow(() -> new NotFoundException("Proveedor no encontrado: " + req.proveedorId()));

    producto.getProveedores().add(proveedor);
    producto.setStock(producto.getStock() + req.cantidad());
    productoRepository.save(producto);

    return new EntradaAlmacenResponse(
        producto.getId(), producto.getNombre(), producto.getStock());
  }

  private ProveedorResponse toResponse(Proveedor p) {
    return new ProveedorResponse(p.getId(), p.getNombre(), p.getNit(), p.getTelefono());
  }
}
