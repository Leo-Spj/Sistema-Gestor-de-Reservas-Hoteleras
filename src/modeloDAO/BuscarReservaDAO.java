
package modeloDAO;

import Configuracion.Conexion;
import Interfaces.BuscarReservaInterfaz;
import Modelo.BuscarReserva;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BuscarReservaDAO implements BuscarReservaInterfaz{
    
    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public boolean crear(BuscarReserva c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(BuscarReserva a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(BuscarReserva e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<BuscarReserva> buscarTodo( ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public ArrayList<BuscarReserva> buscarTodo( int DNI) {
        ArrayList<BuscarReserva> brs = new ArrayList<>();
        try {
            con = new Conexion();
            conn = con.getConectar();
            
            String query = "SELECT * FROM funcion_buscar_reservas_DNI(?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, DNI);
            rs = ps.executeQuery();
            while (rs.next()) {
                BuscarReserva br = new BuscarReserva();

                br.setIdReserva(rs.getInt(1));
                br.setEstado(rs.getString(2));
                br.setSucursal(rs.getString(3));
                br.setDniEmpleado(rs.getString(4));
                br.setIdHabitacion(rs.getInt(5));
                br.setPuerta(rs.getString(6));
                br.setTipo(rs.getString(7));
                br.setPrecioxNoche(rs.getDouble(8));
                br.setInicio(rs.getString(9));
                br.setFin(rs.getString(10));
                br.setNroNoche(rs.getInt(11));
                br.setTotal(rs.getDouble(12));

                brs.add(br);
            }
            rs.close();  
        } catch (Exception e) {
            
            System.out.println("Error al buscar reserva: " + e.getMessage());
        }
        
        
        
        
        return brs;
    }

    @Override
    public BuscarReserva buscarUno(BuscarReserva b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
