-- Hello Kitty MicroMarket — datos de prueba (temática Sanrio)
-- Compatible con H2 (perfil dev) y MySQL (perfil prod)

INSERT INTO categorias (id, nombre, descripcion) VALUES
  (1, 'Peluches Sanrio', 'Peluches oficiales Hello Kitty, My Melody y amigos'),
  (2, 'Papelería kawaii', 'Cuadernos, stickers y clips con temática Hello Kitty'),
  (3, 'Accesorios', 'Lazos, bolsos y artículos de moda cute');

INSERT INTO proveedores (id, nombre, nit, telefono) VALUES
  (1, 'Sanrio Global Supply', '900123456-7', '+81-3-0000-0001'),
  (2, 'Lazo Rosa Importaciones', '800987654-3', '+57-1-5550100');

INSERT INTO productos (id, nombre, codigo_barras, precio, stock, activo, categoria_id) VALUES
  (1, 'Peluche Hello Kitty Classic', '8801234567890', 129900.00, 50, 1, 1),
  (2, 'Cuaderno My Melody A5', '8801234567891', 24900.00, 118, 1, 2),
  (3, 'Lazo rojo con campanita', '8801234567892', 15900.00, 200, 1, 3);

INSERT INTO producto_proveedor (producto_id, proveedor_id) VALUES
  (1, 1),
  (2, 2),
  (3, 2);

INSERT INTO empleados (id, cedula, nombre, cargo, fecha_ingreso, salario) VALUES
  (1, '1000123456', 'Kitty White', 'ADMINISTRADOR', '2024-01-15', 3500000.00),
  (2, '1000654321', 'Cinnamoroll Helper', 'CAJERO', '2024-06-01', 1800000.00),
  (3, '1000789012', 'Kuromi Stock', 'AUXILIAR', '2024-09-10', 1600000.00);

INSERT INTO ventas (id, fecha, empleado_id, subtotal, iva, total) VALUES
  (1, '2025-04-07 10:30:00', 2, 49800.00, 9462.00, 59262.00);

INSERT INTO detalle_ventas (id, venta_id, producto_id, cantidad, precio_unitario) VALUES
  (1, 1, 2, 2, 24900.00);
