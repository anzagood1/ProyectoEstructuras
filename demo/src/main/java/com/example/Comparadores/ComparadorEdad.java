package com.example.Comparadores;

import java.util.Comparator;

import com.example.Users.Contacto;

public class ComparadorEdad implements Comparator<Contacto> {
    
    //Compara la edad de los contactos con el campo Cumpleaños
    @Override
    public int compare(Contacto c1, Contacto c2){
        if (c2.getFechasDeInteres().containsKey("Cumpleanios") &&  c1.getFechasDeInteres().containsKey("Cumpleanios")){
            return c2.getFechasDeInteres().get("Cumpleanios").compareTo( c1.getFechasDeInteres().get("Cumpleanios"));
        }
        if(!c1.getFechasDeInteres().containsKey("Cumpleanios")){
            return 1;
        }
        if(!c2.getFechasDeInteres().containsKey("Cumpleanios")){
            return -1;
        }
        else{
            return 0;
        }
    }   
}
