/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Leo
 */
public class Cliente extends Persona{

    // Constructor
    public Cliente(int DNI, String nombre, String apellido, String celular) {
        super(DNI, nombre, apellido, celular);
    }
    // Constructor vacio, igual tenemos que usar las variables del padre con "super"
    public Cliente() {
        super(0, "", "", "");
    }    
}
