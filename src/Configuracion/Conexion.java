/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private Connection connection;
    private DatabaseConfig databaseConfig; //con esto traemos las credenciales ocultas

    public Conexion(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public Connection getConectar() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //linea no necesaria, para versiones antiguas de JDBC (pre-JDBC 4.0)
            
            String server = databaseConfig.getServer();
            String databaseName = databaseConfig.getDatabaseName();
            String username = databaseConfig.getUsername();
            String password = databaseConfig.getPassword();

            String connectionString = String.format("jdbc:sqlserver://%s.database.windows.net:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                    server, databaseName, username, password);

            connection = DriverManager.getConnection(connectionString);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return connection;
    }
}
