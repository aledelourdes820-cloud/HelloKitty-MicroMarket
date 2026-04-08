package com.sanrio.micromarket.repository;

import com.sanrio.micromarket.model.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

  boolean existsByNit(String nit);

  boolean existsByNitAndIdNot(String nit, Long id);
}
