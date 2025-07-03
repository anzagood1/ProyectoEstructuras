package com.example.Users;

import java.util.Map;

public class Persona extends Contacto{
    private String apellido;

    //Constructor de la clase
    public Persona(int id, Map<String, String> telefonos, Map<String, String> direcciones,Map<String, String> redesSociales, Map<String, String> fechasDeInteres, String apellido){
        super(id, telefonos, direcciones, redesSociales, fechasDeInteres);
        this.apellido = apellido;
    }

    //Metodo para mostrar la informacion del contacto en pantalla
    public void mostrarContacto(){
        System.out.println("Id del Contacto: " + id);
        System.out.println("");

        System.out.println("Nombre del Contacto");
        System.out.println(nombre + " " + apellido);
        System.out.println("");

        System.out.println("Telefonos del Contacto");
        for (Map.Entry<String, String> entry : telefonos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("");

        System.out.println("Direcciones del Contacto");
        for (Map.Entry<String, String> entry : direcciones.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("");

        System.out.println("Redes del Contacto");
        for (Map.Entry<String, String> entry : redesSociales.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("");

        System.out.println("Fechas del Contacto");
        for (Map.Entry<String, String> entry : fechasDeInteres.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    //Getter del atributo apellido
    public String getApellido() {
        return apellido;
    }
}
