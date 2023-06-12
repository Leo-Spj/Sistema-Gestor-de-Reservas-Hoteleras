# Reservas Hoteleras

Bienvenido al proyecto de Reservas Hoteleras, un sistema de reservas de hoteles desarrollado en Java como parte del curso de Programación Orientada a Objetos. Este proyecto utiliza una base de datos alojada en SQL Azure para almacenar la información relacionada con los hoteles, las habitaciones y las reservas.

## Estructura de carpetas

El proyecto sigue una estructura de carpetas organizada en los siguientes directorios:

- `configuracion`: Contiene la configuración del proyecto, como archivos de configuración o propiedades.
- `controlador`: Contiene los controladores del proyecto, encargados de manejar las interacciones entre el modelo y las vistas.
- `modelo`: Contiene las clases del modelo de dominio del proyecto, como las clases de Hotel, Habitación, Reserva, etc.
- `vistas`: Contiene las vistas del proyecto, como las interfaces de usuario y las pantallas de visualización.

## Requisitos

Antes de ejecutar el proyecto, asegúrate de tener instalados los siguientes requisitos:

- Java Development Kit (JDK) 8 o superior.
- Se debe tener instalado el [driver de SQL Server para Java](https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15).
- Un entorno de desarrollo integrado (IDE) compatible con Java, recomendamos NetBeans 17 o superior.
- Acceso a una base de datos SQL Azure. Puedes obtener una cuenta gratuita de Azure y crear una base de datos para este proyecto, con nuestra estructura de tablas y datos de prueba, siguiendo los pasos descritos en el siguiente artículo: [Crear una base de datos SQL Azure](https://docs.microsoft.com/en-us/azure/azure-sql/database/single-database-create-quickstart?tabs=azure-portal).
- Deberá configurar el archivo `config.properties` con los datos de conexión a la base de datos SQL Azure. Puede encontrar un ejemplo de este archivo [aquí](https://github.com/Leo-Spj/Java-POO-UTP/blob/main/Base%20de%20Datos/config.properties), luego este archivo se deberá colocar en la raíz del proyecto.

## Configuración

1. Clona este repositorio en tu máquina local utilizando el siguiente comando:

```bash
git clone https://github.com/Leo-Spj/Java-POO-UTP.git
```	


# Documentación de la base de datos TRAVEL_EASY

## Tabla: empresa_hotelera

| Columna          | Tipo         | Restricciones                |
|------------------|--------------|------------------------------|
| id_empresa_hotel | INT          | PRIMARY KEY, IDENTITY(1, 1)  |
| razon_social     | VARCHAR(255) |                              |
| ruc              | VARCHAR(255) |                              |
| direccion        | VARCHAR(255) |                              |
| telefono_central | VARCHAR(255) |                              |

## Tabla: sucursal

| Columna           | Tipo         | Restricciones                |
|-------------------|--------------|------------------------------|
| id_empresa_hotel  | INT          | DEFAULT 1                    |
| id_sucursal       | INT          | PRIMARY KEY, IDENTITY(1, 1)  |
| nombre            | VARCHAR(255) |                              |
| ciudad            | VARCHAR(255) |                              |
| distrito          | VARCHAR(255) |                              |
| direccion         | VARCHAR(255) |                              |
| telefono_sucursal | VARCHAR(255) |                              |

Restricciones:
- La columna "id_empresa_hotel" tiene una clave externa que hace referencia a la columna "id_empresa_hotel" de la tabla "empresa_hotelera".

## Tabla: tipo_habitacion

| Columna           | Tipo         | Restricciones                |
|-------------------|--------------|------------------------------|
| id_tipo_habitacion | INT         | PRIMARY KEY, IDENTITY(1, 1)  |
| tipo              | VARCHAR(255) |                              |
| capacidad         | INT          |                              |
| descripcion       | VARCHAR(255) |                              |
| precio            | DECIMAL(10, 2) |                            |

## Tabla: habitaciones

| Columna           | Tipo         | Restricciones                |
|-------------------|--------------|------------------------------|
| id_sucursal       | INT          |                              |
| id_habitacion     | INT          | IDENTITY(1, 1), PRIMARY KEY  |
| piso              | INT          |                              |
| puerta            | INT          |                              |
| id_tipo_habitacion | INT         |                              |

Restricciones:
- La columna "id_sucursal" tiene una clave externa que hace referencia a la columna "id_sucursal" de la tabla "sucursal".
- La columna "id_tipo_habitacion" tiene una clave externa que hace referencia a la columna "id_tipo_habitacion" de la tabla "tipo_habitacion".

## Tabla: cargos

| Columna           | Tipo         | Restricciones                |
|-------------------|--------------|------------------------------|
| id_cargo          | INT          | PRIMARY KEY, IDENTITY(1, 1)  |
| nombre            | VARCHAR(255) |                              |
| descripcion       | VARCHAR(255) |                              |

## Tabla: empleados

| Columna           | Tipo         | Restricciones                |
|-------------------|--------------|------------------------------|
| id_empleado       | INT          | PRIMARY KEY, IDENTITY(1, 1)  |
| id_sucursal       | INT          |                              |
| id_cargo          | INT          |                              |
| dni_empleado      | INT          | NOT NULL, UNIQUE             |
| nombre            | VARCHAR(255) |                              |
| apellido          | VARCHAR(255) |                              |
| celular           | VARCHAR(255) |                              |
| contrasena        | VARCHAR(255) |                              |

Restricciones:
- La columna "id_sucursal" tiene una clave externa que hace referencia a la columna "id_sucursal" de la tabla "sucursal".
- La columna "id_cargo" tiene una clave externa que hace referencia a la columna "id_cargo" de la tabla "cargos".

## Tabla: clientes

| Columna           | Tipo         | Restricciones                |
|-------------------|--------------|------------------------------|
| dni_cliente       | INT          | PRIMARY KEY                  |
| nombre            | VARCHAR(255) |                              |
| apellido          | VARCHAR(255) |                              |
| celular           | VARCHAR(255) |                              |

## Tabla: reserva

| Columna           | Tipo         | Restricciones                |
|-------------------|--------------|------------------------------|
| id_reserva        | INT          | PRIMARY KEY, IDENTITY(1, 1)  |
| id_habitacion     | INT          |                              |
| id_empleado       | INT          |                              |
| dni_cliente       | INT          |                              |
| fecha_inicio      | DATE         |                              |
| fecha_fin         | DATE         |                              |
| estado            | VARCHAR(255) | DEFAULT 'Pendiente'          |

Restricciones:
- La columna "id_habitacion" tiene una clave externa que hace referencia a la columna "id_habitacion" de la tabla "habitaciones".
- La columna "id_empleado" tiene una clave externa que hace referencia a la columna "id_empleado" de la tabla "empleados".
- La columna "dni_cliente" tiene una clave externa que hace referencia a la columna "dni_cliente" de la tabla "clientes".

## Tabla: boleta

| Columna           | Tipo         | Restricciones                |
|-------------------|--------------|------------------------------|
| id_boleta         | INT          | PRIMARY KEY, IDENTITY(1, 1)  |
| id_reserva        | INT          |                              |
| fecha_emision     | DATE         |                              |
| concepto          | VARCHAR(255) |                              |
| cantidad_dias     | INT          |                              |
| cupon             | DECIMAL(10, 2) |                            |
| monto_total       | DECIMAL(10, 2) |                            |

Restricciones:
- La columna "id_reserva" tiene una clave externa que hace referencia a la columna "id_reserva" de la tabla "reserva".
