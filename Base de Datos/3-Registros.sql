
/*
---- Insertando registros de prueba ----
*/

-- USE Ventas2023
-- DROP DATABASE TRAVEL_EASY

USE TRAVEL_EASY;
GO


-- Será la UNICA y PRINCIPAL empresa de la base de datos
EXECUTE sp_ingresar_nueva_empresa_hotelera 'Travel Easy Software', '934449808803', 'Av. Universidad Tecnológica del Perú 20', '930045566';


-- Insertar registros en sucursal
INSERT INTO sucursal (nombre, ciudad, distrito, direccion, telefono_sucursal)
VALUES
('Hotel TravelEasy Centro', 'Lima', 'Centro', 'Av. Principal 123', '346567033'),
('Hotel TravelEasy Miraflores', 'Lima', 'Miraflores', 'Calle Secundaria 456', '129452871'),
('Hotel TravelEasy San Isidro', 'Lima', 'San Isidro', 'Av. Principal 789', '560341773');

-- Insertar registros en tipo_habitacion
INSERT INTO tipo_habitacion (tipo, capacidad, descripcion, precio)
VALUES
('Individual', 1, 'Habitación individual estándar', 50.00),
('Doble', 2, 'Habitación doble estándar', 70.00),
('Suite', 2, 'Suite de lujo', 150.00),
('Familiar', 5, 'Habitación familiar con una cama doble y tres camas simples', 130.00);

-- Insertar registros en habitaciones
-- Sucursal 1 (Hotel TravelEasy Centro)
INSERT INTO habitaciones (id_sucursal, piso, puerta, id_tipo_habitacion)
VALUES
(1, 1, 1, 1),
(1, 1, 2, 1),
(1, 2, 1, 2),
(1, 2, 2, 2),
(1, 3, 1, 3),
(1, 3, 2, 3),
(1, 4, 1, 4);

-- Sucursal 2 (Hotel TravelEasy Miraflores)
INSERT INTO habitaciones (id_sucursal, piso, puerta, id_tipo_habitacion)
VALUES
(2, 1, 1, 1),
(2, 1, 2, 1),
(2, 2, 1, 2),
(2, 2, 2, 2),
(2, 3, 1, 3),
(2, 3, 2, 3),
(2, 4, 1, 4);

-- Sucursal 3 (Hotel TravelEasy San Isidro)
INSERT INTO habitaciones (id_sucursal, piso, puerta, id_tipo_habitacion)
VALUES
(3, 1, 1, 1),
(3, 1, 2, 1),
(3, 2, 1, 2),
(3, 2, 2, 2),
(3, 3, 1, 3),
(3, 3, 2, 3),
(3, 4, 1, 4);

-- Insertar los cargo
INSERT INTO cargos (nombre, descripcion) 
VALUES
('Recepcionista', 'Encargado de la recepción del hotel'),
('Administrador', 'Encargado de la administración del hotel'),
('Limpieza', 'Encargado de la limpieza del hotel'),
('Seguridad', 'Encargado de la seguridad del hotel');
 
-- Insertar registros en empleados para cada sucural
-- Sucursal 1 (Hotel TravelEasy Centro)
INSERT INTO empleados (id_sucursal, dni_empleado, id_cargo, nombre, apellido, celular)
VALUES
(1, '99945671', 1, 'Claudia', 'Fiestas', '987004321'),
(1, '999123654', 1, 'Patricia', 'Bikini', '912356000'),
(1, '99954322', 2, 'Ivan', 'Gómez', '912347708'),
(1, '99981233', 3, 'Cecilia', 'Rodríguez', '944886789'),
(1, '99918764', 4, 'Laura', 'Muñoz', '937709812');

--ingresando administrador
INSERT INTO empleados (id_sucursal, id_cargo, dni_empleado, nombre, apellido, celular, contrasena)
VALUES (1, 2, 0000, 'Yuliana', 'Jauregui Rosas', '987987987', '0000');

-- Sucursal 2 (Hotel TravelEasy Miraflores)
INSERT INTO empleados (id_sucursal, dni_empleado, id_cargo, nombre, apellido, celular)
VALUES
(2, '88845671', 1, 'Juan', 'Fiestas', '934504321'),
(2, '888123654', 1, 'Ana', 'Luna', '912377400'),
(2, '88854322', 2, 'Matias', 'Gómez', '912356708'),
(2, '88881233', 3, 'Cecilia', 'Guitton', '995786789'),
(2, '88818764', 4, 'Leonardo', 'Muñoz', '937717412');

-- Sucursal 3 (Hotel TravelEasy San Isidro)
INSERT INTO empleados (id_sucursal, dni_empleado, id_cargo, nombre, apellido, celular)
VALUES
(3, '77745671', 1, 'Claudia', 'Hurtado', '923854321'),
(3, '777123654', 1, 'Juana', 'Arevalo', '912399100'),
(3, '77754322', 2, 'Franco', 'Gómez', '912007708'),
(3, '77781233', 3, 'Camila', 'Rodríguez', '944338789'),
(3, '77718764', 4, 'Paola', 'Zapater', '930049812');

EXEC AgregarContrasenaPorDNI 99945671, '0000';
EXEC AgregarContrasenaPorDNI 999123654, '1111';
EXEC AgregarContrasenaPorDNI 99954322, '22222';
EXEC AgregarContrasenaPorDNI 88845671, '3333';
EXEC AgregarContrasenaPorDNI 888123654, '4444';
EXEC AgregarContrasenaPorDNI 88854322, '5555';
EXEC AgregarContrasenaPorDNI 77745671, '6666';
EXEC AgregarContrasenaPorDNI 777123654, '7777';
EXEC AgregarContrasenaPorDNI 77754322, '8888';

-- Insertar registros en clientes
INSERT INTO clientes (dni_cliente, nombre, apellido, celular)
VALUES
('12345678', 'Juan', 'Pérez', '987654321'),
('87654321', 'María', 'Gómez', '912345678'),
('56781234', 'Pedro', 'Rodríguez', '923456789'),
('43218765', 'Laura', 'López', '934567812'),
('98765432', 'Carlos', 'García', '945678123'),
('76543218', 'Ana', 'Martínez', '956781234'),
('32187654', 'Sofía', 'Hernández', '967812345');

-- realizando reservas
INSERT INTO reserva (id_habitacion, id_empleado, dni_cliente, fecha_inicio, fecha_fin)
VALUES
(1, 1, '12345678', '2023-06-13', '2023-06-14'),
(2, 2, '87654321', '2023-06-12', '2023-06-15'), 
(3, 1, '56781234', '2023-06-11', '2023-06-13'), --  habitacion 3, él sale el 13. No compiten por la habitacion
(3, 1, '43218765', '2023-06-13', '2023-06-14'), --  habitacion 3, él entra el 13. No compiten por la habitacion
(5, 2, '98765432', '2023-06-11', '2023-06-14'), 
(7, 2, '76543218', '2023-06-10', '2023-06-13'), -- compiten por la habitacion 7
(7, 1, '32187654', '2023-06-12', '2023-06-14'); -- compiten por la habitacion 7

