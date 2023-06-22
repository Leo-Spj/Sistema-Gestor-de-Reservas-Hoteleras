/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.ArrayList;

/**
 *
 * @author Leo
 */

//uso genéricos (T: Tipo)
public interface CRUD <T>{
    
    //CRUD
    public boolean crear(T c);
    public boolean actualizar(T a);
    public boolean eliminar(T e);
    
    public ArrayList<T> buscarTodo();
    public T buscarUno(int DNI);
    
}
