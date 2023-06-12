/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Leo
 */
public class DatabaseConfig {

    private Properties properties;

    public DatabaseConfig() {

        properties = new Properties();

        try ( FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getServer() {
        return properties.getProperty("database.server");
    }

    public String getDatabaseName() {
        return properties.getProperty("database.name");
    }

    public String getUsername() {
        return properties.getProperty("database.username");
    }

    public String getPassword() {
        return properties.getProperty("database.password");
    }
}
