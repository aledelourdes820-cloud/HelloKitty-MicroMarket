package com.sanrio.micromarket.dto.categoria;

import com.sanrio.micromarket.dto.producto.ProductoResumenResponse;
import java.util.List;

public record CategoriaResponse(
    Long id, String nombre, String descripcion, List<ProductoResumenResponse> productos) {}
