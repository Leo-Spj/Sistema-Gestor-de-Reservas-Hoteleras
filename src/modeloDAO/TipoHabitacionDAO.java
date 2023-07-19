package modeloDAO;

import Configuracion.Conexion;
import Interfaces.TipoHabitacionInterfaz;
import Modelo.Sucursal;
import Modelo.TipoHabitacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TipoHabitacionDAO implements TipoHabitacionInterfaz {

    /*
    Base de datos de TipoHabitacion:

    CREATE TABLE tipo_habitacion (
        id_tipo_habitacion INT PRIMARY KEY IDENTITY(1, 1),
        tipo VARCHAR(255),
        capacidad INT,
        descripcion VARCHAR(255),
        precio DECIMAL(10, 2)
    );
    */

    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    @Override
    public boolean crear(TipoHabitacion c) {
        return false;
    }
    public boolean crearTipoHabitacion(String tipo,int capacidad,String descripcion,double precio) {
                try {
             con = new Conexion();
             conn = con.getConectar();

             String query = "INSERT INTO tipo_habitacion (tipo, capacidad, descripcion, precio) VALUES (?, ?, ?, ?)";

             ps = conn.prepareStatement(query);
             ps.setString(1, tipo);
             ps.setInt(2, capacidad);
             ps.setString(3, descripcion);
             ps.setDouble(4, precio);

             ps.executeUpdate();

             return true;
         } catch (Exception e) {
             System.out.println("Error al crear tipo de habitación: " + e.getMessage());
             return false;
         }
    }

    @Override
    public boolean actualizar(TipoHabitacion a) {
        return false;
    }

    @Override
    public boolean eliminar(TipoHabitacion e) {
        return false;
    }

    @Override
    public ArrayList<TipoHabitacion> buscarTodo() {

        ArrayList<TipoHabitacion> th = new ArrayList<>();

        try{
            con = new Conexion();
            conn = con.getConectar();

            String query = "SELECT * FROM tipo_habitacion";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                TipoHabitacion t = new TipoHabitacion();

                t.setIdTipoHabitacion(rs.getInt("id_tipo_habitacion"));
                t.setTipo(rs.getString("tipo"));
                t.setCapacidad(rs.getInt("capacidad"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setPrecio(rs.getDouble("precio"));

                th.add(t);
            }

        } catch (Exception e) {
            System.out.println("Error al buscar todas las sucursales: " + e.getMessage());
        }

        return th;
    }

    @Override
    public TipoHabitacion buscarUno(TipoHabitacion b) {
        return null;
    }
}
