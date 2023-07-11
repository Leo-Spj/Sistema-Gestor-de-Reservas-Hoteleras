package modeloDAO;

import Configuracion.Conexion;
import Interfaces.BoletaIntefaz;
import Modelo.Boleta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BoletaDAO implements BoletaIntefaz {

    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public boolean crear(Boleta c) {
        return false;
    }
    // se genera una boleta de esta manera: "EXEC sp_generar_boleta 8, '2023-06-23', 15.00;" fecha de emision dia actual
    public boolean confirmarPago(int idReserva, String fechaEmision, double descuento){
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "EXEC sp_generar_boleta ?, ?, ?";

            ps = conn.prepareStatement(query);
            ps.setInt(1, idReserva);
            ps.setString(2, fechaEmision);
            ps.setDouble(3, descuento);

            //usando executeUpdate porque no es un select, NO usar executeQuery     ------------ OJO -------------
            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Error al generar boleta: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Boleta a) {
        return false;
    }

    @Override
    public boolean eliminar(Boleta e) {
        return false;
    }

    @Override
    public ArrayList<Boleta> buscarTodo() {
        return null;
    }

    @Override
    public Boleta buscarUno(Boleta b) {
        return null;
    }
    // se busca una boleta de esta manera: "EXEC sp_reporte_boletas 8;"
    /*
    retorna:
    "id_boleta": 1,
    "id_reserva": 8,
    "estado": "Pagado",
    "Nombre": "Leonardo Espejo",
    "dni_cliente": 76548632,
    "fecha_emision": "2023-06-23T00:00:00",
    "concepto": "Tipo de habitacion: Individual",
    "Precio por noche": 50.00,
    "fecha_inicio": "2023-07-24T00:00:00",
    "fecha_fin": "2023-07-27T00:00:00",
    "Cantidad de noches": 3,
    "Descuento en Soles": 15.00,
    "monto_total": 135.00
     */

    public Boleta reporteBoleta(int nReserva){
        Boleta b = new Boleta();
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "EXEC sp_reporte_boletas ?";

            ps = conn.prepareStatement(query);
            ps.setInt(1, nReserva);

            //usando executeUpdate porque no es un select, NO usar executeQuery     ------------ OJO -------------
            rs = ps.executeQuery();

            while (rs.next()){
                b.setId_boleta(rs.getInt("id_boleta"));
                b.setId_reserva(rs.getInt("id_reserva"));
                b.setEstado(rs.getString("estado"));
                b.setNombre(rs.getString("Nombre"));
                b.setDni_cliente(rs.getInt("dni_cliente"));
                b.setFecha_emision(rs.getString("fecha_emision"));
                b.setConcepto(rs.getString("concepto"));
                b.setPrecio_por_noche( rs.getDouble("Precio por noche"));
                b.setFecha_inicio(rs.getString("fecha_inicio"));
                b.setFecha_fin(rs.getString("fecha_fin"));
                b.setCantidad_de_noches(rs.getInt("Cantidad de noches"));
                b.setDescuento_en_Soles(rs.getDouble("Descuento en Soles"));
                b.setMonto_total(rs.getDouble("monto_total"));
            }

        } catch (Exception e) {
            System.out.println("Error al generar boleta: " + e.getMessage());
        }
        return b;
    }
}
