package modeloDAO;

import Configuracion.Conexion;
import Interfaces.TipoHabitacionInterfaz;
import Modelo.Sucursal;
import Modelo.TipoHabitacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

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
    public int crearTipoHabitacion(String tipo, int capacidad, String descripcion, double precio) {
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "INSERT INTO tipo_habitacion (tipo, capacidad, descripcion, precio) VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tipo);
            ps.setInt(2, capacidad);
            ps.setString(3, descripcion);
            ps.setDouble(4, precio);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                // Obtener el id_tipo_habitacion generado por la base de datos
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear tipo de habitación: " + e.getMessage());
        }

        return -1; // Retorna -1 en caso de error
    }


    public void guardarCambios(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();

        try {
            // Recorrer las filas de la tabla y guardar los cambios en la base de datos
            for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
                String tipo = tableModel.getValueAt(rowIndex, 1).toString();
                int capacidad = Integer.parseInt(tableModel.getValueAt(rowIndex, 2).toString());
                String descripcion = tableModel.getValueAt(rowIndex, 3).toString();
                double precio = Double.parseDouble(tableModel.getValueAt(rowIndex, 4).toString());

                // Crear la consulta SQL para actualizar los registros
                String query = "UPDATE tblTipoHabitacionesCreadas SET capacidad = ?, descripcion = ?, precio = ? WHERE tipo = ?";
                PreparedStatement statement = conn.prepareStatement(query);

                // Establecer los valores de los parámetros de la consulta
                statement.setInt(1, capacidad);
                statement.setString(2, descripcion);
                statement.setDouble(3, precio);
                statement.setString(4, tipo);

                // Ejecutar la consulta para actualizar los registros
                statement.executeUpdate();
            }


        } catch (Exception e) {
            // Manejar el error en caso de fallo en la conexión o la consulta
            System.out.println("Error al guardar los cambios en la base de datos: " + e.getMessage());
        }
    }

    @Override
    public boolean actualizar(TipoHabitacion a) {
        return false;
    }
    public boolean actualizarTipoHabitacion(String tipo,int capacidad,String descripcion,double precio) {
            try {
                con = new Conexion();
                conn = con.getConectar();
                String query = "EXEC ActualizarTipoHabitacion ?, ?, ?, ?";

                ps = conn.prepareStatement(query);
                ps.setString(1, tipo);
                ps.setInt(2, capacidad);
                ps.setString(3, descripcion);
                ps.setDouble(4, precio);

                boolean hasResult = ps.execute();

                return hasResult;
            } catch (Exception e) {
                System.out.println("Error al actualizar tipo de habitación: " + e.getMessage());
                return false;
            }
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
