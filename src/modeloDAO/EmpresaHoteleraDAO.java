package modeloDAO;

import Configuracion.Conexion;
import Interfaces.EmpresaHoteleraInterfaz;
import Modelo.EmpresaHotelera;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpresaHoteleraDAO implements EmpresaHoteleraInterfaz {

    /*
       La tabla de la base de datos es:

        CREATE TABLE empresa_hotelera (
            id_empresa_hotel INT PRIMARY KEY IDENTITY(1, 1),
            razon_social VARCHAR(255),
            ruc VARCHAR(255),
            direccion VARCHAR(255),
            telefono_central VARCHAR(255)
        );
    */
    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    ResultSet rs;
    Statement st;


    @Override
    public boolean crear(EmpresaHotelera c) {
        return false;
    }

    @Override
    public boolean actualizar(EmpresaHotelera a) {
        return false;
    }

    @Override
    public boolean eliminar(EmpresaHotelera e) {
        return false;
    }

    @Override
    public ArrayList<EmpresaHotelera> buscarTodo() {
        return null;
    }

    @Override
    public EmpresaHotelera buscarUno(EmpresaHotelera b) {
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "SELECT * FROM empresa_hotelera WHERE id_empresa_hotel = 1";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                b.setRazonSocial(rs.getString("razon_social"));
                b.setRuc(rs.getString("ruc"));
                b.setDireccion(rs.getString("direccion"));
                b.setTelefonoCentral(rs.getString("telefono_central"));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("Error al obtener la empresa hotelera");
        }

        return b;
    }
}
