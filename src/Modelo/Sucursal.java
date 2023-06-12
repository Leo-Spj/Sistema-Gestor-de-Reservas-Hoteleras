/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Leo
 */
public class Sucursal {
    private int idSucursal;
    private EmpresaHotelera empresaHotelera;
    private String nombre;
    private String ciudad;
    private String distrito;
    private String direccion;
    private String telefonoSucursal;

    /**
     * @return the idSucursal
     */
    public int getIdSucursal() {
        return idSucursal;
    }

    /**
     * @param idSucursal the idSucursal to set
     */
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    /**
     * @return the empresaHotelera
     */
    public EmpresaHotelera getEmpresaHotelera() {
        return empresaHotelera;
    }

    /**
     * @param empresaHotelera the empresaHotelera to set
     */
    public void setEmpresaHotelera(EmpresaHotelera empresaHotelera) {
        this.empresaHotelera = empresaHotelera;
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
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the distrito
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * @param distrito the distrito to set
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefonoSucursal
     */
    public String getTelefonoSucursal() {
        return telefonoSucursal;
    }

    /**
     * @param telefonoSucursal the telefonoSucursal to set
     */
    public void setTelefonoSucursal(String telefonoSucursal) {
        this.telefonoSucursal = telefonoSucursal;
    }
}
