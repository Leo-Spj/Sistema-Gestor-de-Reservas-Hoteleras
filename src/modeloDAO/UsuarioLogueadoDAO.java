/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import Configuracion.Conexion;
import Configuracion.DatabaseConfig;
import Interfaces.UsuarioLogueadoInterfaz;
import Modelo.UsuarioLogueado;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Leo
 */
public class UsuarioLogueadoDAO implements UsuarioLogueadoInterfaz{
       
    DatabaseConfig databaseConfig = new DatabaseConfig();
    Conexion conexion = new Conexion(databaseConfig); //nuestra clase
    Connection connection; //de la biblioteca
    PreparedStatement ps;
    ResultSet rs;
    UsuarioLogueado ul;
    
    @Override
    public boolean crear(UsuarioLogueado ul) {

        try {
            String query = "SELECT * FROM fnVerificarCredenciales(?, ?)";
            
            connection = (Connection) conexion.getConectar();
            
            
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

    @Override
    public UsuarioLogueado buscarUno(int DNI) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
