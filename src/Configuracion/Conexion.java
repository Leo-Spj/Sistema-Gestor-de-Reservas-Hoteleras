/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private Connection conn;
    DatabaseConfig databaseConfig = new DatabaseConfig();
    
    public Connection getConectar() throws SQLException {
        
        String server = databaseConfig.getServer();
        String databaseName = databaseConfig.getDatabaseName();
        String username = databaseConfig.getUsername();
        String password = databaseConfig.getPassword();

        String connectionString = String.format("jdbc:sqlserver://traveleasy-utp.database.windows.net:1433;database=TravelEsay;user=Java-POO-UTP@traveleasy-utp;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", 
                server, databaseName);

        conn = DriverManager.getConnection(connectionString, username, password);
        
        return conn;
    }
}
