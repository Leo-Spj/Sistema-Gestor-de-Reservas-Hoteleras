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

    private int id_boleta;
    private int id_reserva;
    private String estado;
    private String Nombre;
    private int dni_cliente;
    private String fecha_emision;
    private String concepto;
    private int id_habitacion;
    private double Precio_por_noche;
    private String fecha_inicio;
    private String fecha_fin;
    private int Cantidad_de_noches;
    private double Descuento_en_Soles;
    private double monto_total;

    public int getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(int id_boleta) {
        this.id_boleta = id_boleta;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(int dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public double getPrecio_por_noche() {
        return Precio_por_noche;
    }

    public void setPrecio_por_noche(double precio_por_noche) {
        Precio_por_noche = precio_por_noche;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getCantidad_de_noches() {
        return Cantidad_de_noches;
    }

    public void setCantidad_de_noches(int cantidad_de_noches) {
        Cantidad_de_noches = cantidad_de_noches;
    }

    public double getDescuento_en_Soles() {
        return Descuento_en_Soles;
    }

    public void setDescuento_en_Soles(double descuento_en_Soles) {
        Descuento_en_Soles = descuento_en_Soles;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    @Override
    public String toString() {
       return   "----------------Boleta--------------------" + "\n" +
                "| N° boleta: " + id_boleta+"\n" +
                "| N° reserva: " + id_reserva+ "\n" +
                "| Estado: " + estado + "\n" +
                "| Nombre: " + Nombre +"\n" +
                "| DNI: " + dni_cliente +"\n" +
                "| Fecha de emision: " + fecha_emision +"\n" +
                "| Concepto: " + concepto +"\n" +
                "| Precio por noche: " + Precio_por_noche +"\n" +
                "| Fecha de inicio: " + fecha_inicio +"\n" +
                "| Fecha de fin: " + fecha_fin +"\n" +
                "| Cantidad de noches: " + Cantidad_de_noches +"\n" +
                "| Descuento en soles: " + Descuento_en_Soles + "\n" +
                "| ---------------------------------------- |" + "\n" +
                "   Monto total: " + monto_total + "\n" +
                "| ---------------------------------------- |";
    }
}
