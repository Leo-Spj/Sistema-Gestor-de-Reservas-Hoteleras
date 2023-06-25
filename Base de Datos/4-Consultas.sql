

-- USE Ventas2023
-- DROP DATABASE TRAVEL_EASY

USE TRAVEL_EASY;
GO


--funcion que obtiene LOS id_empleado y el dni_empreado pero solo de aquellos que tengan el cargo de recepcionista o administrador
SELECT * FROM aptos_login()
SELECT * FROM clientes
/*
Consultas simples
*/

SELECT *
FROM empleados
WHERE id_sucursal = 1;

SELECT monto_total
FROM boleta
WHERE id_reserva = 8;

SELECT s.nombre, e.telefono_central
FROM sucursal s
JOIN empresa_hotelera e ON s.id_empresa_hotel = e.id_empresa_hotel;

SELECT COUNT(*) AS reservas_totales
FROM reserva r
JOIN habitaciones h ON r.id_habitacion = h.id_habitacion
WHERE h.id_sucursal = 1;

SELECT e.id_empleado , e.dni_empleado ,e.nombre, e.apellido, e.celular, s.nombre AS sucursal, s.id_sucursal
FROM empleados AS e
INNER JOIN sucursal AS s ON e.id_sucursal = s.id_sucursal
WHERE e.id_cargo = 1;

/*
Subconsultas
*/

SELECT *
FROM habitaciones
WHERE id_sucursal = 1
  AND id_habitacion NOT IN (
    SELECT id_habitacion
    FROM reserva
    WHERE fecha_inicio <= GETDATE() AND fecha_fin >= GETDATE()
  );

SELECT *
FROM reserva
WHERE dni_cliente = (
    SELECT dni_cliente
    FROM clientes
    WHERE nombre = 'Leonardo'
);

SELECT *
FROM empleados
WHERE id_cargo = (
    SELECT id_cargo
    FROM cargos
    WHERE nombre = 'Administrador'
);

SELECT *
FROM sucursal
WHERE id_sucursal IN (
    SELECT id_sucursal
    FROM habitaciones
    WHERE id_tipo_habitacion IN (
        SELECT id_tipo_habitacion
        FROM tipo_habitacion
        WHERE capacidad > 3
    )
);

SELECT *
FROM reserva
WHERE id_empleado IN (
    SELECT id_empleado
    FROM empleados
    WHERE id_cargo = (
        SELECT id_cargo
        FROM cargos
        WHERE nombre = 'Recepcionista'
    )
);



/*
Consultas ÚTILES:
*/


--Mostrar los recepcionistas de cada sucursal
SELECT e.id_empleado , e.dni_empleado ,e.nombre, e.apellido, e.celular, s.nombre AS sucursal, s.id_sucursal
FROM empleados AS e
INNER JOIN sucursal AS s ON e.id_sucursal = s.id_sucursal
WHERE e.id_cargo = 1;


SELECT * FROM reserva

-- Para buscar las habitaciones disponibles de una sucursal
-- colocar el id de la sucursal, fecha de inicio y fecha de fin
-- Si nadie ha pagado se muestran todas las habitaciones
EXEC sp_buscar_habitaciones_disponibles 1, '2023-06-10', '2023-06-13';


-- Con la busqueda por dni en las reservas podemos obtener el id_reserva y poder
-- utilizarla para generar la boleta:
-- SOLO busca las reservas con estado "Pendiente" (las que están activas)
SELECT * FROM funcion_buscar_reservas_DNI( 76543218 );


-- Genero la boleta mediante el stored procedure sp_generar_boleta (previamente habiendo buscado el id de la reserva):
-- (id_reserva, fecha de emision, descuento en soles)
EXECUTE sp_generar_boleta 6, '2023-06-09', 50.00;


-- Verifico que se haya generado la boleta:
-- se puede observar que al pagar la reserva con id 6 anuló la 7 (competían entre fechas)
SELECT * FROM reserva; 


-- Reporte de boletas usando el ID_RESERVA (no con el dni ya que pueden haber varias) estilo reporte de boleta para imprimir
EXECUTE sp_reporte_boletas 6;

-- Muestras A TODOS quienes han pagado + id_habitacion: 
SELECT * FROM funcion_reporte_boletas()
 

-- Muestra todas las reservas ordenadas por ESTADO de Pago
SELECT * FROM fn_reporte_reservas()
ORDER BY
  CASE
    WHEN estado LIKE 'Pagado' THEN 1   -- Valores que comienzan por "Pa"
    WHEN estado LIKE 'Pendiente' THEN 2   
    ELSE 3                          -- Otros valores
  END,
estado;



-------------


-- Muestra la cantidad de habitaciones por sucursal
SELECT s.nombre AS nombre_sucursal, COUNT(h.id_habitacion) AS total_habitaciones
FROM sucursal s
LEFT JOIN habitaciones h ON s.id_sucursal = h.id_sucursal
GROUP BY s.nombre, s.id_sucursal;

-- Muestra solo el top 10 de los clientes con mayor cantidad de reservas pagadas (boletas) 
SELECT TOP 10 c.dni_cliente, CONCAT(c.nombre, ' ', c.apellido) AS nombre_completo, COUNT(b.id_boleta) AS 'Reservas pagadas - Boletas'
FROM clientes c
LEFT JOIN reserva r ON c.dni_cliente = r.dni_cliente
LEFT JOIN boleta b ON r.id_reserva = b.id_reserva
GROUP BY c.dni_cliente, CONCAT(c.nombre, ' ', c.apellido)
HAVING COUNT(b.id_boleta) > 0
ORDER BY COUNT(b.id_boleta) DESC;