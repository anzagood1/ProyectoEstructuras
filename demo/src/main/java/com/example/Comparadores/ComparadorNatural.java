package com.example.Comparadores;

import java.util.Comparator;

import com.example.Users.Contacto;

public class ComparadorNatural implements Comparator<Contacto> {
    
    //Compara los Ids de los contactos
    @Override
    public int compare(Contacto c1, Contacto c2){
        return Integer.compare(c1.getId(), c2.getId());
    }
}
