package com.sanrio.micromarket.service;

import com.sanrio.micromarket.dto.venta.DetalleVentaResponse;
import com.sanrio.micromarket.dto.venta.VentaItemRequest;
import com.sanrio.micromarket.dto.venta.VentaRequest;
import com.sanrio.micromarket.dto.venta.VentaResponse;
import com.sanrio.micromarket.exception.BadRequestException;
import com.sanrio.micromarket.exception.NotFoundException;
import com.sanrio.micromarket.model.entity.DetalleVenta;
import com.sanrio.micromarket.model.entity.Empleado;
import com.sanrio.micromarket.model.entity.Producto;
import com.sanrio.micromarket.model.entity.Venta;
import com.sanrio.micromarket.repository.EmpleadoRepository;
import com.sanrio.micromarket.repository.ProductoRepository;
import com.sanrio.micromarket.repository.VentaRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaService {

  private static final BigDecimal TASA_IVA = new BigDecimal("0.19");

  private final VentaRepository ventaRepository;
  private final EmpleadoRepository empleadoRepository;
  private final ProductoRepository productoRepository;

  public VentaService(
      VentaRepository ventaRepository,
      EmpleadoRepository empleadoRepository,
      ProductoRepository productoRepository) {
    this.ventaRepository = ventaRepository;
    this.empleadoRepository = empleadoRepository;
    this.productoRepository = productoRepository;
  }

  @Transactional(readOnly = true)
  public VentaResponse obtener(Long id) {
    Venta v =
        ventaRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Venta no encontrada: " + id));
    return toResponse(v);
  }

  @Transactional
  public VentaResponse registrar(VentaRequest req) {
    Empleado empleado =
        empleadoRepository
            .findById(req.empleadoId())
            .orElseThrow(() -> new NotFoundException("Empleado no encontrado: " + req.empleadoId()));

    List<LineaCalculada> lineas = new ArrayList<>();
    BigDecimal subtotal = BigDecimal.ZERO;

    for (VentaItemRequest item : req.items()) {
      Producto p =
          productoRepository
              .findByIdAndActivoTrue(item.productoId())
              .orElseThrow(
                  () ->
                      new NotFoundException(
                          "Producto no encontrado o inactivo: " + item.productoId()));
      if (p.getStock() < item.cantidad()) {
        throw new BadRequestException(
            "Stock insuficiente para el producto «"
                + p.getNombre()
                + "». Disponible: "
                + p.getStock()
                + ", solicitado: "
                + item.cantidad());
      }
      BigDecimal precioUnit = p.getPrecio().setScale(2, RoundingMode.HALF_UP);
      BigDecimal lineaTotal =
          precioUnit.multiply(BigDecimal.valueOf(item.cantidad())).setScale(2, RoundingMode.HALF_UP);
      subtotal = subtotal.add(lineaTotal);
      lineas.add(new LineaCalculada(p, item.cantidad(), precioUnit));
    }

    BigDecimal subtotalRed = subtotal.setScale(2, RoundingMode.HALF_UP);
    BigDecimal iva =
        subtotalRed.multiply(TASA_IVA).setScale(2, RoundingMode.HALF_UP);
    BigDecimal total = subtotalRed.add(iva).setScale(2, RoundingMode.HALF_UP);

    Venta venta = new Venta();
    venta.setFecha(LocalDateTime.now());
    venta.setEmpleado(empleado);
    venta.setSubtotal(subtotalRed);
    venta.setIva(iva);
    venta.setTotal(total);

    for (LineaCalculada lc : lineas) {
      DetalleVenta dv = new DetalleVenta();
      dv.setVenta(venta);
      dv.setProducto(lc.producto());
      dv.setCantidad(lc.cantidad());
      dv.setPrecioUnitario(lc.precioUnitario());
      venta.getDetalles().add(dv);

      Producto p = lc.producto();
      p.setStock(p.getStock() - lc.cantidad());
      productoRepository.save(p);
    }

    venta = ventaRepository.save(venta);
    return toResponse(venta);
  }

  private VentaResponse toResponse(Venta v) {
    List<DetalleVentaResponse> detalles =
        v.getDetalles().stream()
            .map(
                d ->
                    new DetalleVentaResponse(
                        d.getProducto().getId(),
                        d.getProducto().getNombre(),
                        d.getCantidad(),
                        d.getPrecioUnitario()))
            .toList();
    return new VentaResponse(
        v.getId(),
        v.getFecha(),
        v.getEmpleado().getId(),
        v.getEmpleado().getNombre(),
        v.getSubtotal(),
        v.getIva(),
        v.getTotal(),
        detalles);
  }

  private record LineaCalculada(Producto producto, int cantidad, BigDecimal precioUnitario) {}
}
