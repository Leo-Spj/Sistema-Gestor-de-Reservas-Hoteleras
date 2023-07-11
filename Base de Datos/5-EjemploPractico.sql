
/*
-------- PROBANDO base de datos con un EJEMPLO de uso COTIDIANO: ----------------
*/

-- USE Ventas2023
-- DROP DATABASE TRAVEL_EASY

USE TRAVEL_EASY;
GO

--comprobar si el empleado es valido
SELECT * FROM fnVerificarCredenciales(99945671, '0000');

-- Lo primero que hace una persona es buscar una sucursal cercana a sus necesidades:
--obteniendo sucursal
SELECT * FROM sucursal

-- Lo siguiente que hace es preguntar si tiene habitaciones disponibles entre las fechas que desea:
-- y puede incluso acotar que necesita un tipo de habitacion especifica
-- Buscando habitaciones disponibles: (escoge la sucursal de miraflores)
-- de las 7 habitaciones solo se  muestran 6 ya que una ya está reservada entre esas fechas:
EXEC sp_buscar_habitaciones_disponibles 1, '2023-07-24', '2023-07-27';

-- Ingresándome como nuevo cliente:
EXEC sp_ingresar_nuevo_cliente '76548632', 'Leonardo', 'Espejo', '940937610'

-- Se realiza la reserva que solicité con el ID_HABITACION correspondiente:
-- la habitacion 3 compite con una reserva existente aun no pagada
EXEC sp_realizar_reserva 2,'888123654', '76548632', '2023-07-24', '2023-07-27';


-- Se busca mi reservas por DNI para obtener el id de la reserva y poder generar la boleta: (puede que se muestren varias reservas y solo se escoge una a pagar)
SELECT * FROM funcion_buscar_reservas_DNI(76548632); -- Busca solo las PENDIENTES
-- id_reserva,	estado,	sucursal,	Responsable de la reserva,	id_habitacion	Habitacion,	tipo,	Precio por noche,	fecha_inicio,	fecha_fin,	Numero de noches,	Total a Pagar (sin descuento).
-- id_reserva, sucursal, responsable, habitacion, tipo, precio por noche, fecha_inicio, fecha_fin, numero de noches, total a pagar (sin descuento)
--Así se puede buscar TODAS las reservas del cliente
SELECT * FROM fn_reporte_reservas() WHERE dni_cliente = '76548632' 

-- Generando boleta:
-- OJO no ejecutar 2 veces ya que no tiene condicional para verificar si ya se genero la boleta (en el programa no sucedera esto)
EXEC sp_generar_boleta 8, '2023-06-23', 15.00;
-- EXEC sp_generar_boleta 12, '2023-06-04', 15.00;


-- Reporte de boletas:
EXEC sp_reporte_boletas 8;


-- Reporte de BOLETAS (sin parametros):
SELECT * FROM funcion_reporte_boletas()

 -- Muestra todas las reservas ordenadas por ESTADO de Pago
 -- el pago de la boleta con id_reserva 8 anuló la id_reserva 3 no pagada:
SELECT * FROM fn_reporte_reservas()
ORDER BY
  CASE
    WHEN estado LIKE 'Pa%' THEN 1   -- Valores que comienzan por "Pa"
    WHEN estado LIKE 'Pe%' THEN 2    -- Valores que comienzan por "A"
    ELSE 3                          -- Otros valores
  END,
  estado;





----NO EJECUTAR-------
----NO EJECUTAR-------
----NO EJECUTAR:-------

-- creando una nueva sucursal
INSERT INTO sucursal (nombre, ciudad, distrito, direccion, telefono_sucursal)
VALUES ('Hotel TravelEasy Nuevo', 'Nueva', 'Nuevo', 'Av. nueva 123', '900111000');
--ver las sucursales
SELECT * FROM sucursal

-- creando una nueva habitacion general
EXECUTE sp_ingresar_nuevo_tipo_habitacion 'Suite Jacuzzi ', 2, 'Habitación para pareja Suite - Jacuzzi', 200.00;

--visualizar los tipos de habitacion
SELECT * FROM tipo_habitacion

-- insertar habitacion a a sucursal con id_sucursal = 4
/*
CREATE PROCEDURE sp_ingresar_nueva_habitacion
    @id_sucursal INT,
    @piso INT,
    @puerta INT,
    @id_tipo_habitacion INT
    */
-- ingresar 10 habitaciones en 5 pisos
EXECUTE sp_ingresar_nueva_habitacion 4, 1, 1, 4;
EXECUTE sp_ingresar_nueva_habitacion 4, 1, 2, 4;
EXECUTE sp_ingresar_nueva_habitacion 4, 2, 1, 2;
EXECUTE sp_ingresar_nueva_habitacion 4, 2, 2, 2;
EXECUTE sp_ingresar_nueva_habitacion 4, 3, 1, 3;
EXECUTE sp_ingresar_nueva_habitacion 4, 3, 2, 3;
EXECUTE sp_ingresar_nueva_habitacion 4, 4, 1, 1;
EXECUTE sp_ingresar_nueva_habitacion 4, 4, 2, 1;
EXECUTE sp_ingresar_nueva_habitacion 4, 5, 1, 5;
EXECUTE sp_ingresar_nueva_habitacion 4, 5, 2, 5;


-- visualizar las habitaciones de la sucursal con id_sucursal = 4
SELECT * FROM habitaciones WHERE id_sucursal = 4