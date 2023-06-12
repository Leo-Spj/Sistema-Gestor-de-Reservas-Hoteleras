/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Leo
 */
public class Boleta {
    private int idBoleta;
    private Reserva reserva;
    private String fechaEmision;
    private String concepto;
    private int cantidadDias;
    private double cupon;
    private double montoTotal;

    /**
     * @return the idBoleta
     */
    public int getIdBoleta() {
        return idBoleta;
    }

    /**
     * @param idBoleta the idBoleta to set
     */
    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    /**
     * @return the reserva
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the concepto
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * @param concepto the concepto to set
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     * @return the cantidadDias
     */
    public int getCantidadDias() {
        return cantidadDias;
    }

    /**
     * @param cantidadDias the cantidadDias to set
     */
    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    /**
     * @return the cupon
     */
    public double getCupon() {
        return cupon;
    }

    /**
     * @param cupon the cupon to set
     */
    public void setCupon(double cupon) {
        this.cupon = cupon;
    }

    /**
     * @return the montoTotal
     */
    public double getMontoTotal() {
        return montoTotal;
    }

    /**
     * @param montoTotal the montoTotal to set
     */
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
