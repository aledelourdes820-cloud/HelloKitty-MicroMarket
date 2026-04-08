package com.sanrio.micromarket.service;

import com.sanrio.micromarket.dto.empleado.EmpleadoRequest;
import com.sanrio.micromarket.dto.empleado.EmpleadoResponse;
import com.sanrio.micromarket.exception.BadRequestException;
import com.sanrio.micromarket.exception.NotFoundException;
import com.sanrio.micromarket.model.entity.Empleado;
import com.sanrio.micromarket.model.enums.Cargo;
import com.sanrio.micromarket.repository.EmpleadoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoService {

  private final EmpleadoRepository empleadoRepository;

  public EmpleadoService(EmpleadoRepository empleadoRepository) {
    this.empleadoRepository = empleadoRepository;
  }

  @Transactional(readOnly = true)
  public List<EmpleadoResponse> listar(Cargo cargo, java.time.LocalDate fechaInicio, java.time.LocalDate fechaFin) {
    return empleadoRepository.buscarPorFiltros(cargo, fechaInicio, fechaFin).stream()
        .map(this::toResponse)
        .toList();
  }

  @Transactional(readOnly = true)
  public EmpleadoResponse obtener(Long id) {
    Empleado e =
        empleadoRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Empleado no encontrado: " + id));
    return toResponse(e);
  }

  @Transactional
  public EmpleadoResponse crear(EmpleadoRequest req) {
    if (empleadoRepository.existsByCedula(req.cedula())) {
      throw new BadRequestException("Ya existe un empleado con la cédula indicada");
    }
    Empleado e = new Empleado();
    e.setCedula(req.cedula());
    e.setNombre(req.nombre());
    e.setCargo(req.cargo());
    e.setFechaIngreso(req.fechaIngreso());
    e.setSalario(req.salario());
    e = empleadoRepository.save(e);
    return toResponse(e);
  }

  @Transactional
  public EmpleadoResponse actualizar(Long id, EmpleadoRequest req) {
    Empleado e =
        empleadoRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Empleado no encontrado: " + id));
    if (empleadoRepository.existsByCedulaAndIdNot(req.cedula(), id)) {
      throw new BadRequestException("Ya existe otro empleado con la cédula indicada");
    }
    e.setCedula(req.cedula());
    e.setNombre(req.nombre());
    e.setCargo(req.cargo());
    e.setFechaIngreso(req.fechaIngreso());
    e.setSalario(req.salario());
    e = empleadoRepository.save(e);
    return toResponse(e);
  }

  @Transactional
  public void eliminar(Long id) {
    if (!empleadoRepository.existsById(id)) {
      throw new NotFoundException("Empleado no encontrado: " + id);
    }
    empleadoRepository.deleteById(id);
  }

  private EmpleadoResponse toResponse(Empleado e) {
    return new EmpleadoResponse(
        e.getId(), e.getCedula(), e.getNombre(), e.getCargo(), e.getFechaIngreso(), e.getSalario());
  }
}
