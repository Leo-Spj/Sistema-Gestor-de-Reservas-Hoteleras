/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import Configuracion.Conexion;
import Interfaces.HabitacionInterfaz;
import Modelo.Habitacion;
import Modelo.TipoHabitacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brandonluismenesessolorzano
 */
public class HabitacionDAO  implements HabitacionInterfaz{
    
    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    @Override
    public boolean crear(Habitacion c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public boolean crearHabitacion(int id_sucursal, int piso, int puerta, int id_tipo_habitacion) {
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "INSERT INTO habitaciones (id_sucursal, piso, puerta, id_tipo_habitacion) VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(query);
            ps.setInt(1, id_sucursal);
            ps.setInt(2, piso);
            ps.setInt(3, puerta);
            ps.setInt(4, id_tipo_habitacion);

            int rowsInserted = ps.executeUpdate();

            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println("Error al crear habitación: " + e.getMessage());
            return false;
        }
    }
    public boolean crearHabitacion_creHab(int id_sucursal, int piso, int puerta, int id_tipo_habitacion) {
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "INSERT INTO habitaciones (id_sucursal, piso, puerta, id_tipo_habitacion) VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(query);
            ps.setInt(1, id_sucursal);
            ps.setInt(2, piso);
            ps.setInt(3, puerta);
            ps.setInt(4, id_tipo_habitacion);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Error al crear habitación: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    public boolean guardarCambios(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();
        try {
            for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            int sucursalId = (int) tableModel.getValueAt(rowIndex, 0);
            int piso = (int) tableModel.getValueAt(rowIndex, 1);
            int puerta = (int) tableModel.getValueAt(rowIndex, 2);
            int tipoHabitacionId = (int) tableModel.getValueAt(rowIndex, 3);

            // Crear la consulta SQL para actualizar los registros
            String query = "UPDATE tblHabitacionesCreadas SET id_sucursal = ?, piso = ?, puerta = ?, id_tipo_habitacion = ? WHERE id_habitacion = ?";
            PreparedStatement statement = conn.prepareStatement(query);

            // Establecer los valores de los parámetros de la consulta
            statement.setInt(1, sucursalId);
            statement.setInt(2, piso);
            statement.setInt(3, puerta);
            statement.setInt(4, tipoHabitacionId);

            // Ejecutar la consulta para actualizar los registros
            statement.executeUpdate();
        }

        return true; // Retorna true si todos los cambios se guardaron correctamente
    } catch (Exception e) {
        // Manejar el error en caso de fallo en la conexión o la consulta
        System.out.println("Error al guardar los cambios en la base de datos: " + e.getMessage());
        return false;
    }
    }


    @Override
    public boolean actualizar(Habitacion a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public boolean actualizarHabitacion( int id_habitacion, int id_sucursal, int piso, int puerta, int id_tipo_habitacion){
            try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "EXEC sp_actualizar_habitacion ?, ?, ?, ?, ?";

            ps = conn.prepareStatement(query);
            ps.setInt(1, id_habitacion);
            ps.setInt(2, id_sucursal);
            ps.setInt(3, piso);
            ps.setInt(4, puerta);
            ps.setInt(5, id_tipo_habitacion);

            int rowsUpdated = ps.executeUpdate(); // Utilizamos executeUpdate() para una consulta UPDATE

            return rowsUpdated > 0; // Retornar true si se actualizó al menos una fila
        } catch (Exception e) {
            System.out.println("Error al actualizar la habitación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Habitacion e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    


    @Override
    public ArrayList<Habitacion> buscarTodo() {
        ArrayList<Habitacion> habitaciones = new ArrayList<>();

        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "SELECT * FROM habitaciones";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Habitacion habitacion = new Habitacion();

                // Obtener los valores de las columnas de la tabla y asignarlos al objeto Habitacion
                habitacion.setIdHabitacion(rs.getInt("id_habitacion"));
                habitacion.setSucursalId(rs.getInt("id_sucursal"));
                habitacion.setPiso(rs.getInt("piso"));
                habitacion.setPuerta(rs.getInt("puerta"));
                habitacion.setTipoHabitacionId(rs.getInt("id_tipo_habitacion"));

                habitaciones.add(habitacion);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar todas las habitaciones: " + e.getMessage());
        }

        return habitaciones;
    }
    public ArrayList<Habitacion> buscarTodoPorSucursal(int id_sucursal) {
    ArrayList<Habitacion> habitaciones = new ArrayList<>();

        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "SELECT * FROM habitaciones WHERE id_sucursal = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id_sucursal);
            rs = ps.executeQuery();

            while (rs.next()) {
                Habitacion habitacion = new Habitacion();

                // Obtener los valores de las columnas de la tabla y asignarlos al objeto Habitacion
                habitacion.setIdHabitacion(rs.getInt("id_habitacion"));
                habitacion.setSucursalId(rs.getInt("id_sucursal"));
                habitacion.setPiso(rs.getInt("piso"));
                habitacion.setPuerta(rs.getInt("puerta"));
                habitacion.setTipoHabitacionId(rs.getInt("id_tipo_habitacion"));

                habitaciones.add(habitacion);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar todas las habitaciones de la sucursal: " + e.getMessage());
        }

        return habitaciones;
    }

    public ArrayList<Habitacion> buscarTodo(int id_sucursal, int id_tipo_habitacion) {
            ArrayList<Habitacion> habitaciones = new ArrayList<>();

            try {
                con = new Conexion();
                conn = con.getConectar();

                String query;
                PreparedStatement ps;

                if (id_tipo_habitacion == 0) {
                    query = "SELECT * FROM habitaciones WHERE id_sucursal = ?";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, id_sucursal);
                } else {
                    query = "SELECT * FROM habitaciones WHERE id_sucursal = ? AND id_tipo_habitacion = ?";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, id_sucursal);
                    ps.setInt(2, id_tipo_habitacion);
                }

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Habitacion habitacion = new Habitacion();

                    habitacion.setIdHabitacion(rs.getInt("id_habitacion"));
                    habitacion.setSucursalId(rs.getInt("id_sucursal"));
                    habitacion.setPiso(rs.getInt("piso"));
                    habitacion.setPuerta(rs.getInt("puerta"));
                    habitacion.setTipoHabitacionId(rs.getInt("id_tipo_habitacion"));

                    habitaciones.add(habitacion);
                }
            } catch (Exception e) {
                System.out.println("Error al buscar todas las habitaciones: " + e.getMessage());
            }

            return habitaciones;
}


    @Override
    public Habitacion buscarUno(Habitacion b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
