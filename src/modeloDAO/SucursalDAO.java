/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import Configuracion.Conexion;
import Interfaces.SucursalInterfaz;
import Modelo.Sucursal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Leo
 */
public class SucursalDAO implements SucursalInterfaz{
    /*
    Base de datos de la sucursal:

    CREATE TABLE sucursal (
        id_empresa_hotel INT DEFAULT 1, --SOLO SE PERMITIRÁ UN HOTEL
        id_sucursal INT PRIMARY KEY IDENTITY(1, 1),
        nombre VARCHAR(255),
        ciudad VARCHAR(255),
        distrito VARCHAR(255),
        direccion VARCHAR(255),
        telefono_sucursal VARCHAR(255),

        FOREIGN KEY (id_empresa_hotel) REFERENCES empresa_hotelera (id_empresa_hotel)
    );

    */


    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public boolean crear(Sucursal c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Sucursal a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Sucursal e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Sucursal> buscarTodo() {
        //buscar todas las sucursales y devolver el arraylist
        ArrayList<Sucursal> sucursales = new ArrayList<>();

        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "SELECT * FROM sucursal";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Sucursal sucursal = new Sucursal();

                sucursal.setIdSucursal(rs.getInt("id_sucursal"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setIdSucursal(rs.getInt("id_sucursal"));
                sucursal.setCiudad(rs.getString("ciudad"));
                sucursal.setDistrito(rs.getString("distrito"));
                sucursal.setDireccion(rs.getString("direccion"));
                sucursal.setTelefonoSucursal(rs.getString("telefono_sucursal"));

                sucursales.add(sucursal);
            }

            rs.close();

        } catch (Exception e) {
            System.out.println("Error al buscar todas las sucursales: " + e.getMessage());
        }

        return sucursales;
    }

    @Override
    public Sucursal buscarUno(Sucursal b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
