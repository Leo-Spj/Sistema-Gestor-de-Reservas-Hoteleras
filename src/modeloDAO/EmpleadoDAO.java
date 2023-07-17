/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import Configuracion.Conexion;
import Interfaces.EmpleadoInterfaz;
import Modelo.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author brandonluismenesessolorzano
 */
public class EmpleadoDAO implements EmpleadoInterfaz{
    
    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public boolean crear(Empleado c) {
        return false;
    }
    public boolean crearEmpleados(int dni_empleado, String nombre, String apellido, String celular, int id_sucursal, int id_cargo, String contrasena){
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query =" INSERT INTO empleados (dni_empleado, nombre, apellido, celular, id_sucursal, id_cargo, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(query);
            ps.setInt(1, dni_empleado);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, celular);
            ps.setInt(5, id_sucursal);
            ps.setInt(6, id_cargo);
            ps.setString(7, contrasena);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Empleado a) {
        return false;
    }

    @Override
    public boolean eliminar(Empleado e) {
        return false;
    }

    @Override
    public ArrayList<Empleado> buscarTodo() {
        return null;
    }

    @Override
    public Empleado buscarUno(Empleado b) {
        return null;
    }
    
}
