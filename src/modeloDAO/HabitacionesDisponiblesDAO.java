/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import Configuracion.Conexion;
import Interfaces.HabitacionesDisponiblesInterfaz;
import Modelo.Habitacion;
import Modelo.HabitacionDisponible;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Leo
 */
public class HabitacionesDisponiblesDAO implements HabitacionesDisponiblesInterfaz{

    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public boolean crear(HabitacionDisponible c) {
        return false;
    }
    public boolean crearHabitacion() {
        return false;
    }

    @Override
    public boolean actualizar(HabitacionDisponible a) {
        return false;
    }

    @Override
    public boolean eliminar(HabitacionDisponible e) {
        return false;
    }

    @Override
    public ArrayList<HabitacionDisponible> buscarTodo() {
        return null;
    }

    public ArrayList<HabitacionDisponible> buscarTodo(int idSucursal, String fechaInicio, String fechaFin) {

        ArrayList<HabitacionDisponible> hd = new ArrayList<>();

        try{
            con = new Conexion();
            conn = con.getConectar();

            String query = "EXEC sp_buscar_habitaciones_disponibles ?, ?, ?;";
            ps = conn.prepareStatement(query);
            ps.setInt(1, idSucursal);
            ps.setString(2, fechaInicio);
            ps.setString(3, fechaFin);

            rs = ps.executeQuery();

            //la busqueda de habitaaciones disponibles contiene 8 columnas
            while(rs.next()){
                HabitacionDisponible h = new HabitacionDisponible();
                h.setId_habitacion(rs.getInt(1));
                h.setTipo(rs.getString(2));
                h.setCapacidad(rs.getInt(3));
                h.setDescripcion(rs.getString(4));
                h.setHabitacion(rs.getString(5));
                h.setPrecio(rs.getDouble(6));
                h.setReservasSinPagar(rs.getInt(7));
                h.setMaxDuracionAfectada(rs.getInt(8));

                hd.add(h);
            }

        } catch (Exception e){
            System.out.println("Error al buscar habitaciones disponibles: " + e.getMessage());
        }

        return hd;
    }

    @Override
    public HabitacionDisponible buscarUno(HabitacionDisponible b) {
        return null;
    }
}
