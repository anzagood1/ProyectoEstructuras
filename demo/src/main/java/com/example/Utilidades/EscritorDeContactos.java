package com.example.Utilidades;

import java.util.HashMap;
import java.util.Scanner;

import com.example.Users.Contacto;
import com.example.Users.Empresa;
import com.example.Users.Persona;
import com.example.Users.Usuario;

public class EscritorDeContactos {
    public static Scanner scanner = new Scanner(System.in);

    //Pide el ingreso de datos para añadir un objeto Contacto a la lista correspondiente
    public static Contacto escribirContactos(Usuario usuario) {
        System.out.println("El contacto que desea añadir es empresa o persona (E/P):");
        String tipoContacto = scanner.nextLine().trim().toUpperCase();
        char tipo = tipoContacto.charAt(0);
        String id = usuario.generarCodigoUnico() + "";
        System.out.print("Introduzca el nombre del contacto: ");
        String nombre = capitalizar(scanner.nextLine().trim());
        Contacto contacto;
        switch (tipo) {
            case 'P':
                System.out.print("Introduzca el apellido del contacto: ");
                String apellido = capitalizar(scanner.nextLine().trim());
                HashMap<String, String> telefono = escribirMapa("telefono", new HashMap<String, String>());
                HashMap<String, String> direccion = escribirMapa("direccion", new HashMap<String, String>());
                HashMap<String, String> redesSociales = escribirMapa("red social", new HashMap<String, String>());
                HashMap<String, String> fechasDeInteres = escribirMapa("fecha de interés", new HashMap<String, String>());
                contacto = new Persona(Integer.parseInt(id), telefono, direccion, redesSociales, fechasDeInteres, apellido);
                contacto.setNombre(nombre); // Asignar el nombre completo
                return contacto;
            case 'E':
                HashMap<String, String> telefonoE = escribirMapa("telefono", new HashMap<String, String>());
                HashMap<String, String> direccionE = escribirMapa("direccion", new HashMap<String, String>());
                HashMap<String, String> redesSocialesE = escribirMapa("red social", new HashMap<String, String>());
                HashMap<String, String> fechasDeInteresE = escribirMapa("fecha de interés", new HashMap<String, String>());
                contacto = new Empresa(Integer.parseInt(id), telefonoE, direccionE, redesSocialesE, fechasDeInteresE);
                contacto.setNombre(nombre); // Asignar el nombre completo
                return contacto;
            default:
                System.out.println("Tipo de contacto no válido. Debe ser 'P' para Persona o 'E' para Empresa.");
                return null;
        }
    }

    //Metodo recursivo para obtener un mapa
    public static HashMap<String, String> escribirMapa(String tipoMapa, HashMap<String, String> mapa) {
        String recursivo  = "no";
        System.out.print("Ingrese el tipo de " + tipoMapa + " que desea ingresar: ");
        String tipoDelTipo = capitalizar(scanner.nextLine().trim());
        System.out.print("Ingrese el valor del tipo " + tipoMapa + " de " + tipoDelTipo + ": ");
        String valor = capitalizar(scanner.nextLine().trim());
        mapa.put(tipoDelTipo, valor);
        System.out.print("¿Desea agregar otro elemento a "+ tipoMapa +"? (SI/NO): " );
        recursivo = scanner.nextLine().trim().toLowerCase();
        if (recursivo.equals("si")) {
            escribirMapa(tipoMapa, mapa);
        } 
        return mapa;
    }

    //Metodo para capitalizar la primera letra de un string
    public static String capitalizar(String texto) {
    if (texto == null || texto.isEmpty()) {
        return texto;
    }
    return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
}
}
