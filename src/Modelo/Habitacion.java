/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Leo
 */
public class Habitacion {

    private Sucursal sucursal;
    private TipoHabitacion tipoHabitacion;
    
    private int idHabitacion;
    private int piso;
    private int puerta;
     
    
    /**
     * @return the sucursal
     */
    public String getSucursalNombre() {
        return sucursal.getNombre();
    }
    
    /**
     * @return the tipoHabitacion
     */
    public String getTipoHabitacionNombre() {
        return tipoHabitacion.getTipo();
    }
    
    /**
     * @return the idHabitacion
     */
    public int getIdHabitacion() {
        return idHabitacion;
    }

    /**
     * @param idHabitacion the idHabitacion to set
     */
    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    /**
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }

    /**
     * @return the puerta
     */
    public int getPuerta() {
        return puerta;
    }

    /**
     * @param puerta the puerta to set
     */
    public void setPuerta(int puerta) {
        this.puerta = puerta;
    }

}
