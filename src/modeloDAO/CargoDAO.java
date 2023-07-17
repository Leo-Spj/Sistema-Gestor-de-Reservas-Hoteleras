/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import Configuracion.Conexion;
import Interfaces.CargoInterfaz;
import Modelo.Cargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author brandonluismenesessolorzano
 */
public class CargoDAO implements CargoInterfaz{
    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public boolean crear(Cargo c) {
        return false;
    }

    @Override
    public boolean actualizar(Cargo a) {
        return false;
    }

    @Override
    public boolean eliminar(Cargo e) {
        return false;
    }

    @Override
    public ArrayList<Cargo> buscarTodo() {
        //buscar todas los cargos y devolver el arraylist
        ArrayList<Cargo> cargos = new ArrayList<>();

        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "SELECT * FROM cargos";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("id_cargo"));
                cargo.setNombre(rs.getString("nombre"));
                cargo.setDescripcion(rs.getString("descripcion"));
                cargos.add(cargo);
            }

            rs.close();

        } catch (Exception e) {
            System.out.println("Error al buscar todos los cargos: " + e.getMessage());
        }

        return cargos;
    }

    @Override
    public Cargo buscarUno(Cargo b) {
        return null;
    }
    
}
