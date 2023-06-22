/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import Configuracion.Conexion;
import Configuracion.DatabaseConfig;
import Interfaces.UsuarioLogueadoInterfaz;
import Modelo.UsuarioLogueado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Leo
 */
public class UsuarioLogueadoDAO implements UsuarioLogueadoInterfaz{
       
    DatabaseConfig databaseConfig;
    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    UsuarioLogueado ul;
    
    @Override
    public boolean crear(UsuarioLogueado ul) {

        try {
            databaseConfig = new DatabaseConfig();
            con = new Conexion(databaseConfig);
            conn = con.getConectar();
            
            String query = "SELECT * FROM fnVerificarCredenciales(?, ?)";
            ps = conn.prepareStatement(query);
            //introduciendo credenciales
            ps.setInt(1, ul.getDNI());
            ps.setString(2, ul.getContraseña());
            
            rs = ps.executeQuery();
            
                       
            if (rs.next()) {
                int resultado = rs.getInt("esValido");

                if (resultado == 1) { // 1: acceso aprobado
                    
                    buscarUno(ul); //uso la funcion de consulta e inserto datos
                    
                }
            }
                        
        } catch (Exception e) {
        }
        return false;
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

    public UsuarioLogueado buscarUno(UsuarioLogueado ul) {

        try {
            databaseConfig = new DatabaseConfig();
            con = new Conexion(databaseConfig);
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

    @Override
    public UsuarioLogueado buscarUno(int DNI) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
}
