package com.sanrio.micromarket.repository;

import com.sanrio.micromarket.model.entity.Empleado;
import com.sanrio.micromarket.model.enums.Cargo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

  boolean existsByCedula(String cedula);

  boolean existsByCedulaAndIdNot(String cedula, Long id);

  @Query(
      """
      SELECT e FROM Empleado e WHERE
        (:cargo IS NULL OR e.cargo = :cargo)
        AND (:inicio IS NULL OR e.fechaIngreso >= :inicio)
        AND (:fin IS NULL OR e.fechaIngreso <= :fin)
      """)
  List<Empleado> buscarPorFiltros(
      @Param("cargo") Cargo cargo,
      @Param("inicio") LocalDate inicio,
      @Param("fin") LocalDate fin);
}
