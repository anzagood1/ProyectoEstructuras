package com.example.Comparadores;

import java.util.Comparator;

import com.example.Users.Contacto;

public class ComparadorPorNombre implements Comparator<Contacto>{
    
    //Compara los nombres de los contactos
    @Override
    public int compare(Contacto c1, Contacto c2){
        return c1.getNombre().compareTo(c2.getNombre());
    }
}
