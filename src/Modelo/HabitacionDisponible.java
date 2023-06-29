package Modelo;

public class HabitacionDisponible {
    //la busqueda de habitaaciones disponibles contiene 8 columnas:
    // id_habitacion, tipo, capacidad, descripcion, habitacion, Precio por noche, reservas sin pagar, MaxDuracion Afectada (dias).
    private int id_habitacion;
    private String tipo;
    private int capacidad;
    private String descripcion;
    private String habitacion;
    private double precio;
    private int reservasSinPagar;
    private int maxDuracionAfectada;


    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getReservasSinPagar() {
        return reservasSinPagar;
    }

    public void setReservasSinPagar(int reservasSinPagar) {
        this.reservasSinPagar = reservasSinPagar;
    }

    public int getMaxDuracionAfectada() {
        return maxDuracionAfectada;
    }

    public void setMaxDuracionAfectada(int maxDuracionAfectada) {
        this.maxDuracionAfectada = maxDuracionAfectada;
    }
}
