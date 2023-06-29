/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import Configuracion.Conexion;
import Configuracion.DatabaseConfig;
import Interfaces.UsuarioLogueadoInterfaz;
import Modelo.UsuarioLogueado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Leo
 */
public class UsuarioLogueadoDAO implements UsuarioLogueadoInterfaz{


    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
       
    @Override
    public boolean crear(UsuarioLogueado ul) {
        
        boolean accesoConcedido = false;
        
        con = new Conexion();
        String query = "SELECT * FROM fnVerificarCredenciales(?, ?)";

        try (Connection conn = con.getConectar();
             PreparedStatement ps = conn.prepareStatement(query)) {
            // Introducir credenciales
            ps.setInt(1, ul.getDNI());
            ps.setString(2, ul.getContraseña());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int resultado = rs.getInt("esValido");

                    if (resultado == 1) {
                        accesoConcedido = true;
                        buscarUno(ul);
                    }
                }
            }

        } catch (SQLException e) {
            accesoConcedido = false;
        }
        return accesoConcedido;
    }


    @Override
    public boolean actualizar(UsuarioLogueado a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(UsuarioLogueado e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<UsuarioLogueado> buscarTodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioLogueado buscarUno(UsuarioLogueado ul) {

        try {

            con = new Conexion();
            conn = con.getConectar();
            
            String query = "SELECT * FROM empleados WHERE dni_empleado = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, ul.getDNI());

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                // Obtengo los valores de la fila y los asigno al objeto UsuarioLogueado
                ul.setId_empleado(rs.getInt("id_empleado"));
                ul.setId_sucursal(rs.getInt("id_sucursal"));
                ul.setId_cargo(rs.getInt("id_cargo"));
                ul.setNombre(rs.getString("nombre"));
                ul.setApellido(rs.getString("apellido"));
                ul.setCelular(rs.getString("celular"));
            }

        } catch (Exception e) {
        }
        return ul;
    }
        
}
