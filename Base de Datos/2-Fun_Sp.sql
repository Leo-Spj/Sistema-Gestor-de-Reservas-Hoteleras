-- USE Ventas2023
-- DROP DATABASE TRAVEL_EASY

USE TRAVEL_EASY;
GO


-- asigar contraseña a trabajador por dni:
GO
CREATE PROCEDURE AgregarContrasenaPorDNI
    @dni INT,
    @contrasena VARCHAR(255)
AS
BEGIN
    UPDATE empleados
    SET contrasena = @contrasena
    WHERE dni_empleado = @dni;
END;
GO

--funcion que obtiene LOS dni_empleado y la contraseña pero solo de aquellos que tengan el cargo de recepcionista = 1 o administrador = 2
GO
CREATE FUNCTION aptos_login()
RETURNS TABLE
AS
RETURN
    SELECT dni_empleado, contrasena
    FROM empleados
    WHERE id_cargo = 1 OR id_cargo = 2
GO

select * from aptos_login()


-- credenciales
GO
CREATE FUNCTION fnVerificarCredenciales (
    @dni_empleado INT,
    @contrasena VARCHAR(255)
)
RETURNS @resultado TABLE (
    esValido BIT
)
AS
BEGIN
    DECLARE @esValido BIT

    IF EXISTS (
        SELECT *
        FROM empleados e
        INNER JOIN cargos c ON e.id_cargo = c.id_cargo
        WHERE e.dni_empleado = @dni_empleado
            AND e.contrasena = @contrasena
            AND c.id_cargo IN (1, 2)
    )
    BEGIN
        SET @esValido = 1
    END
    ELSE
    BEGIN
        SET @esValido = 0
    END

    INSERT INTO @resultado (esValido)
    VALUES (@esValido)

    RETURN
END
GO

SELECT * FROM fnVerificarCredenciales(99945671, '0000')

-- Store procedure para ingresar empresa hotelera
GO
CREATE PROCEDURE sp_ingresar_nueva_empresa_hotelera
    @razon_social VARCHAR(255),
    @ruc VARCHAR(255),
    @direccion VARCHAR(255),
    @telefono_central VARCHAR(255)
AS
BEGIN
    INSERT INTO empresa_hotelera (razon_social, ruc, direccion, telefono_central)
    VALUES (@razon_social, @ruc, @direccion, @telefono_central)
END
GO

-- Store procedure para ingresar tipo de habitación
GO
CREATE PROCEDURE sp_ingresar_nuevo_tipo_habitacion
    @tipo VARCHAR(255),
    @capacidad INT,
    @descripcion VARCHAR(255),
    @precio DECIMAL(10, 2)
AS
BEGIN
    INSERT INTO tipo_habitacion (tipo, capacidad, descripcion, precio)
    VALUES (@tipo, @capacidad, @descripcion, @precio)
END
GO

-- Store procedure para ingresar habitaciones
GO
CREATE PROCEDURE sp_ingresar_nueva_habitacion
    @id_sucursal INT,
    @piso INT,
    @puerta INT,
    @id_tipo_habitacion INT
AS
BEGIN
    INSERT INTO habitaciones (id_sucursal, piso, puerta, id_tipo_habitacion)
    VALUES (@id_sucursal, @piso, @puerta, @id_tipo_habitacion)
END
GO

--store procedure para ingresar clientes
GO
CREATE PROCEDURE sp_ingresar_nuevo_cliente
    @dni_cliente VARCHAR(8),
    @nombre VARCHAR(50),
    @apellido VARCHAR(50),
    @celular VARCHAR(9)
AS
BEGIN
    INSERT INTO clientes (dni_cliente, nombre, apellido, celular)
    VALUES (@dni_cliente, @nombre, @apellido, @celular)
END
GO


--store procedure para ingresar reservas
GO
CREATE PROCEDURE sp_realizar_reserva
    @id_habitacion INT,
    @DNIempleado INT,
    @dni_cliente VARCHAR(8),
    @fecha_inicio DATE,
    @fecha_fin DATE
AS
BEGIN
    -- Verificar si el empleado es un recepcionista o un administrador
    IF EXISTS (
        SELECT id_empleado
        FROM empleados 
        WHERE dni_empleado = @DNIempleado
        AND id_cargo IN (1, 2)
    )
    BEGIN
        -- conficional si el dni del cliente existe
        IF EXISTS (
            SELECT dni_cliente
            FROM clientes
            WHERE dni_cliente = @dni_cliente
        )
        BEGIN
            -- Obtener el ID del empleado
            DECLARE @id_empleado INT;
            SET @id_empleado = (SELECT id_empleado 
                                FROM empleados 
                                WHERE dni_empleado = @DNIempleado);

            -- Insertar la reserva en la tabla
            INSERT INTO reserva (id_habitacion, id_empleado, dni_cliente, fecha_inicio, fecha_fin)
            VALUES (@id_habitacion, @id_empleado, @dni_cliente, @fecha_inicio, @fecha_fin);
        END
        ELSE
        BEGIN
            PRINT '¡ El DNI del cliente no existe !';
        END;
    END;
    ELSE
    BEGIN
        PRINT '¡ El empleado NO es un recepcionista o administrador !';
    END;
END;
GO


-- La siguiente funcion busca las habitaciones disponibles de una sucursal mediante fechas de inicio y fin, 
-- esta buscará todas las habitaciones que no esten marcadas como "Pagadas".
-- Solo devolvera los id de las habitaciones disponibles en una tabla

GO
CREATE FUNCTION funcion_buscar_ID_habitaciones_disponibles(
    @id_sucursal INT, 
    @fecha_inicio DATE, 
    @fecha_fin DATE)
RETURNS TABLE
AS
RETURN
    SELECT h.id_habitacion
    FROM habitaciones AS h
    WHERE h.id_habitacion NOT IN (
        SELECT id_habitacion
        FROM reserva
        WHERE (
            (fecha_inicio < @fecha_fin AND fecha_fin > @fecha_inicio)
        )
        AND estado = 'Pagado'

    )
    AND h.id_sucursal = @id_sucursal;
GO

-- Funcion que retorna la cantidad de personas que quieren reservar una habitacion en un rango de fechas
GO
CREATE FUNCTION funcion_buscar_cantidad_personas(
    @id_habitacion INT, 
    @fecha_inicio DATE, 
    @fecha_fin DATE)
RETURNS INT
AS
BEGIN
    DECLARE @cantidad_personas INT;

    SELECT @cantidad_personas = COUNT(*)
    FROM reserva
    WHERE id_habitacion = @id_habitacion
    AND (
        (fecha_inicio < @fecha_fin AND fecha_fin > @fecha_inicio)
    )
    AND estado = 'Pendiente';
    RETURN @cantidad_personas;
END
GO

--funcion que retorne del id_habitacion la cantidad de dias mas alta de una reserva segun el rango de fechas
GO
CREATE FUNCTION funcion_buscar_cantidad_dias(
    @id_habitacion INT, 
    @fecha_inicio DATE, 
    @fecha_fin DATE)
RETURNS INT
AS
BEGIN
    DECLARE @cantidad_dias INT;
-- se debe obtener los dias de la reserva con mayor cantidad de dias en un rango de fechas
    SELECT @cantidad_dias = MAX(DATEDIFF(DAY, fecha_inicio, fecha_fin))
    FROM reserva
    WHERE id_habitacion = @id_habitacion
    AND (
        (fecha_inicio < @fecha_fin AND fecha_fin > @fecha_inicio)
    )
    AND estado = 'Pendiente';
    RETURN @cantidad_dias;
END
GO

-- El siguiente Stored Procedure sirve para la busqueda de habitaciones disponibles por sucursal,
-- esta utiliza la funcion anterior "funcion_buscar_ID_habitaciones_disponibles", 
-- y DEVUELVE las que NO estan como estado 'Pagado' ni entre sus fechas de reserva

GO
CREATE PROCEDURE sp_buscar_habitaciones_disponibles(
    @id_sucursal INT, 
    @fecha_inicio DATE, 
    @fecha_fin DATE)
AS
BEGIN
    SELECT h.id_habitacion, 
    th.tipo, 
    th.capacidad, 
    th.descripcion, 
    CONCAT(h.piso, '-', h.puerta) AS habitacion, 
    th.precio AS 'Precio por noche',
    dbo.funcion_buscar_cantidad_personas(h.id_habitacion, @fecha_inicio, @fecha_fin) AS 'Reservas sin pagar',
    dbo.funcion_buscar_cantidad_dias(h.id_habitacion, @fecha_inicio, @fecha_fin) AS 'MaxDuracion Afectada (dias)'
    FROM habitaciones AS h
    INNER JOIN tipo_habitacion AS th ON h.id_tipo_habitacion = th.id_tipo_habitacion
    WHERE h.id_habitacion IN (
        SELECT id_habitacion
        FROM funcion_buscar_ID_habitaciones_disponibles(@id_sucursal, @fecha_inicio, @fecha_fin)
    )
    ORDER BY th.capacidad, th.tipo; 
END
GO

-- Strored Procedure para buscar por dni a las personas que han realizado reservas con estado "Pendiente" (las que están activas) 
-- (no aparecerán las "Canceladas" o "Pagadas") "sp_buscar_reservas_DNI"

GO
CREATE FUNCTION funcion_buscar_reservas_DNI(
    @dni_cliente VARCHAR(8) )
RETURNS TABLE
AS
RETURN
    SELECT id_reserva, estado, s.nombre AS sucursal, 
        e.dni_empleado AS 'Responsable de la reserva', h.id_habitacion,
        CONCAT(h.piso,'-',h.puerta) AS 'Habitacion',
        th.tipo, th.precio AS 'Precio por noche', fecha_inicio, fecha_fin, 
        DATEDIFF(DAY, r.fecha_inicio,r. fecha_fin) AS 'Numero de noches', 
        (DATEDIFF(DAY, r.fecha_inicio,r. fecha_fin)*th.precio) AS 'Total a Pagar (sin descuento)'
    FROM reserva AS r
    INNER JOIN clientes AS c ON r.dni_cliente = c.dni_cliente
    INNER JOIN habitaciones AS h ON r.id_habitacion = h.id_habitacion
    INNER JOIN sucursal AS s ON h.id_sucursal = s.id_sucursal
    INNER JOIN tipo_habitacion AS th ON h.id_tipo_habitacion = th.id_tipo_habitacion
    FULL JOIN empleados AS e ON r.id_empleado = e.id_empleado
    WHERE r.dni_cliente = @dni_cliente
    AND r.estado = 'Pendiente'
GO

-- Realizo un Stored Procedure para generar la boleta con Cupon de descuento en soles 
-- (si no hay cupon en el programa se le asigna 0 automaticamente) 

GO
CREATE PROCEDURE sp_generar_boleta(
    @id_reserva INT, 
    @fecha_emision DATE, 
    @cupon DECIMAL(10, 2) )
AS
BEGIN
    DECLARE @concepto VARCHAR(255);
    DECLARE @cantidad_dias INT;
    DECLARE @monto_total DECIMAL(10, 2);

    SELECT @concepto = CONCAT('Tipo de habitacion: ', tipo) 
    FROM tipo_habitacion AS th 
    INNER JOIN habitaciones AS h ON th.id_tipo_habitacion = h.id_tipo_habitacion 
    INNER JOIN reserva AS r ON h.id_habitacion = r.id_habitacion 
    WHERE id_reserva = @id_reserva; 
       
    SELECT @cantidad_dias = DATEDIFF(DAY, fecha_inicio, fecha_fin) 
    FROM reserva 
    WHERE id_reserva = @id_reserva;

    SELECT @monto_total = (precio * @cantidad_dias) - @cupon
    FROM tipo_habitacion AS th
    INNER JOIN habitaciones AS h ON th.id_tipo_habitacion = h.id_tipo_habitacion
    INNER JOIN reserva AS r ON h.id_habitacion = r.id_habitacion
    WHERE id_reserva = @id_reserva;

    INSERT INTO boleta (id_reserva, fecha_emision, concepto, cantidad_dias, cupon, monto_total)
    VALUES (@id_reserva, @fecha_emision, @concepto, @cantidad_dias, @cupon, @monto_total);

    UPDATE reserva 
    SET estado = 'Pagado' 
    WHERE id_reserva = @id_reserva;

    -- Tambien se deben colocar todas las reservas que estan como pendientes con estado "Anulado" (YA QUE NUNCA PAGARON), 
    -- solo aquellas fechas de la actual boleta ocupada por el cliente que está pagando.
    DECLARE @fecha_inicio DATE, @fecha_fin DATE;
    SELECT @fecha_inicio = fecha_inicio, @fecha_fin = fecha_fin
    FROM reserva
    WHERE id_reserva = @id_reserva;
    -- Declarar el id de la habitacion escogida por el usuario obteniendo el id de la reserva
    DECLARE @id_habitacion INT;
    SELECT @id_habitacion = id_habitacion
    FROM reserva
    WHERE id_reserva = @id_reserva;

    
    UPDATE reserva
    SET estado = 'Anulado'
    WHERE id_reserva != @id_reserva
    AND (
        (fecha_inicio < @fecha_fin AND fecha_fin > @fecha_inicio)
    )
    AND id_habitacion = @id_habitacion;


END

--FUNCION: devuelve todas las RESERVAS con el EL RECEPCIONISTA RESPONSABLE, 
--eL dni del cliente y su nombre, el estado de la boleta, el monto total, el cupon, la fecha de emision y el concepto
GO
CREATE FUNCTION fn_reporte_reservas()
RETURNS TABLE
AS
RETURN
    SELECT r.id_reserva, r.estado, CONCAT(e.nombre, ' ', e.apellido) AS 'Recepcionista', 
    c.dni_cliente, CONCAT(c.nombre, ' ', c.apellido) AS 'Cliente', th.tipo , h.id_habitacion ,
    (DATEDIFF(DAY, r.fecha_inicio,r. fecha_fin)*th.precio) AS 'Total a Pagar (sin descuento)',
    b.cupon, b.monto_total AS 'Total pagado', b.fecha_emision
    FROM reserva AS r
    LEFT JOIN empleados AS e ON r.id_empleado = e.id_empleado
    LEFT JOIN clientes AS c ON r.dni_cliente = c.dni_cliente
    LEFT JOIN boleta AS b ON r.id_reserva = b.id_reserva
    LEFT JOIN habitaciones AS h ON r.id_habitacion = h.id_habitacion
    LEFT JOIN tipo_habitacion AS th ON h.id_tipo_habitacion = th.id_tipo_habitacion

GO

-- Mediante un Stored Procedure obtengo el "reporte de la boleta", se obtiene el estado de la boleta, si esta pagado o no, el nombre del cliente, etc
-- todos los tados obtenidos aqui son UTILES 
GO
CREATE PROCEDURE sp_reporte_boletas(
    @id_reserva INT )
AS
BEGIN
    SELECT b.id_boleta, r.id_reserva, r.estado, CONCAT( c.nombre, ' ', c.apellido) AS 'Nombre', 
    c.dni_cliente, b.fecha_emision, b.concepto, th.precio AS 'Precio por noche', r.fecha_inicio, r.fecha_fin,
    b.cantidad_dias AS 'Cantidad de noches', b.cupon AS 'Descuento en Soles', b.monto_total
    FROM boleta AS b
    INNER JOIN reserva AS r ON b.id_reserva = r.id_reserva
    INNER JOIN clientes AS c ON r.dni_cliente = c.dni_cliente
	JOIN habitaciones AS h ON r.id_habitacion = h.id_habitacion
    JOIN tipo_habitacion AS th ON h.id_tipo_habitacion = th.id_tipo_habitacion
    WHERE b.id_reserva = @id_reserva;
END

--funcion de reporte de boletas sin parametros
GO
CREATE FUNCTION funcion_reporte_boletas()
RETURNS TABLE
AS
RETURN
    SELECT b.id_boleta, r.id_reserva, r.estado, CONCAT( c.nombre, ' ', c.apellido) AS 'Nombre', 
    c.dni_cliente, b.fecha_emision, b.concepto, h.id_habitacion, th.precio AS 'Precio por noche', r.fecha_inicio, r.fecha_fin,
    b.cantidad_dias AS 'Cantidad de noches', b.cupon AS 'Descuento en Soles', b.monto_total
    FROM boleta AS b
    INNER JOIN reserva AS r ON b.id_reserva = r.id_reserva
    INNER JOIN clientes AS c ON r.dni_cliente = c.dni_cliente
    JOIN habitaciones AS h ON r.id_habitacion = h.id_habitacion
    JOIN tipo_habitacion AS th ON h.id_tipo_habitacion = th.id_tipo_habitacion
GO