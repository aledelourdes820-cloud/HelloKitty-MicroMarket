package com.sanrio.micromarket.repository;

import com.sanrio.micromarket.model.entity.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

  boolean existsByCodigoBarras(String codigoBarras);

  boolean existsByCodigoBarrasAndIdNot(String codigoBarras, Long id);

  List<Producto> findByActivoTrue();

  Optional<Producto> findByIdAndActivoTrue(Long id);
}
