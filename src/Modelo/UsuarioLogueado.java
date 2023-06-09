/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Leo
 */

/*
Script de la base de datos:

    CREATE TABLE empleados (
        id_empleado INT PRIMARY KEY IDENTITY(1, 1),
        id_sucursal INT,
        id_cargo INT,
        dni_empleado INT,
        nombre VARCHAR(255),
        apellido VARCHAR(255),
        celular VARCHAR(255),

        FOREIGN KEY (id_sucursal) REFERENCES sucursal (id_sucursal),
        FOREIGN KEY (id_cargo) REFERENCES cargos (id_cargo)
    )
*/
public class UsuarioLogueado {
    private int id_empleado;
    private int id_sucursal;
    private int id_cargo;
    private int dni_empleado;
    private String nombre;
    private String apellido;
    private String celular;

    /**
     * @return the id_empleado
     */
    public int getId_empleado() {
        return id_empleado;
    }

    /**
     * @param id_empleado the id_empleado to set
     */
    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    /**
     * @return the id_sucursal
     */
    public int getId_sucursal() {
        return id_sucursal;
    }

    /**
     * @param id_sucursal the id_sucursal to set
     */
    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    /**
     * @return the id_cargo
     */
    public int getId_cargo() {
        return id_cargo;
    }

    /**
     * @param id_cargo the id_cargo to set
     */
    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    /**
     * @return the dni_empleado
     */
    public int getDni_empleado() {
        return dni_empleado;
    }

    /**
     * @param dni_empleado the dni_empleado to set
     */
    public void setDni_empleado(int dni_empleado) {
        this.dni_empleado = dni_empleado;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }
    
}
