package modeloDAO;

import Configuracion.Conexion;
import Interfaces.ClienteInterfaz;
import Modelo.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO implements ClienteInterfaz {

    /*
       La tabla de la base de datos es:

        CREATE TABLE clientes (
            dni_cliente INT PRIMARY KEY,
            nombre VARCHAR(255),
            apellido VARCHAR(255),
            celular VARCHAR(255)
        );
    */
    Conexion con; //nuestra clase
    java.sql.Connection conn; //de la biblioteca
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    public boolean crear(Cliente c) {
        return false;
    }

    public boolean crearCliente(int dni_cliente, String nombre, String apellido, String celular){
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "INSERT INTO clientes (dni_cliente, nombre, apellido, celular) VALUES (?, ?, ?, ?)";

            ps = conn.prepareStatement(query);
            ps.setInt(1, dni_cliente);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, celular);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean actualizar(Cliente a) {
        return true;
    }
    public boolean actualizarCliente( String dni_cliente,String nombre, String apellido, String celular) {
        try {
        con = new Conexion();
        conn = con.getConectar();

        String queryActualizarCliente = "EXEC sp_actualizar_cliente ?, ?, ?, ?";

        ps = conn.prepareStatement(queryActualizarCliente);
        ps.setString(1, dni_cliente);
        ps.setString(2, nombre);
        ps.setString(3, apellido);
        ps.setString(4, celular);
        ps.executeUpdate();

        return true;
    } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Cliente e) {
        return false;
    }
    public boolean eliminarCliente(int dni_cliente, String nombre, String apellido, String celular) {
                try {
                con = new Conexion();
                conn = con.getConectar();

                String query = "EXEC sp_eliminar_cliente ?";

                ps = conn.prepareStatement(query);
                ps.setInt(1, dni_cliente);

                ps.executeUpdate();

                return true;
            } catch (Exception e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
                return false;
            }
    }

    @Override
    public ArrayList<Cliente> buscarTodo() {
        return null;
    }

    @Override
    public Cliente buscarUno(Cliente b) {
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "SELECT * FROM clientes WHERE dni_cliente = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, b.getDNI());

            rs = ps.executeQuery();

            while (rs.next()) {
                b.setDNI(rs.getInt("dni_cliente"));
                b.setNombre(rs.getString("nombre"));
                b.setApellido(rs.getString("apellido"));
                b.setCelular(rs.getString("celular"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return b;
    }

    public Cliente buscarCliente(int dni) {
        Cliente cliente = new Cliente();
        try {
            con = new Conexion();
            conn = con.getConectar();

            String query = "SELECT * FROM clientes WHERE dni_cliente = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, dni);

            rs = ps.executeQuery();

            while (rs.next()) {
                cliente.setDNI(rs.getInt("dni_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCelular(rs.getString("celular"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return cliente;
    }

    

}
