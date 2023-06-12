/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Leo
 */
public class Empleado extends Persona {

    private int idEmpleado;
    private Sucursal sucursal;
    private Cargo cargo;
    
    // Constructor
    public Empleado(int idEmpleado, Sucursal sucursal, Cargo cargo, int DNI, String nombre, String apellido, String celular) {
        super(DNI, nombre, apellido, celular);
        this.idEmpleado = idEmpleado;
        this.sucursal = sucursal;
        this.cargo = cargo;
    }
    
    // Constructor vacio, igual tenemos que usar las variables del padre con "super"
    public Empleado() {
        super(0, "", "", "");  // Llama al constructor de la clase padre (Persona)
    }

    /**
     * @return the idEmpleado
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the sucursal
     */
    public Sucursal getSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    
}
