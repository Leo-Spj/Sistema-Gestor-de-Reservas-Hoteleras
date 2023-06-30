package modeloDAO;

import Configuracion.Conexion;
import Interfaces.ReservaInterfaz;
import Modelo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservaDAO implements ReservaInterfaz {

    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public boolean crear(Reserva c) {
        return false;
    }

    //EXEC sp_realizar_reserva 2,'888123654', '76548632', '2023-06-24', '2023-06-27';
    public boolean crearReserva(int idHabitacion, String dniEmpleado, String dniCliente, String fechaInicio, String fechaFin){
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "EXEC sp_realizar_reserva ?, ?, ?, ?, ?";

            ps = conn.prepareStatement(query);
            ps.setInt(1, idHabitacion);
            ps.setString(2, dniEmpleado);
            ps.setString(3, dniCliente);
            ps.setString(4, fechaInicio);
            ps.setString(5, fechaFin);

            rs = ps.executeQuery();

            return true;
        } catch (Exception e) {
            System.out.println("Error al crear reserva: " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean actualizar(Reserva a) {
        return false;
    }

    @Override
    public boolean eliminar(Reserva e) {
        return false;
    }

    @Override
    public ArrayList<Reserva> buscarTodo() {
        return null;
    }

    @Override
    public Reserva buscarUno(Reserva b) {
        return null;
    }
}
