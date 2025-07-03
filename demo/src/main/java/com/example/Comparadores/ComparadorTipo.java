package com.example.Comparadores;

import java.util.Comparator;

import com.example.Users.Contacto;
import com.example.Users.Empresa;
import com.example.Users.Persona;

public class ComparadorTipo implements Comparator<Contacto>{

    //Compara el tipo de cada contacto (Persona o Empresa)
    @Override
    public int compare(Contacto c1, Contacto c2){
        int resultado = 0;
        if(c1 instanceof Empresa && c2 instanceof Empresa){
            resultado = 0;
        }else if(c1 instanceof Empresa && c2 instanceof Persona){
            resultado = 1;
        }else if(c1 instanceof Persona && c2 instanceof Empresa){
            resultado = -1;
        }
        return resultado;
    }
}
