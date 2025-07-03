package com.example.Users;

import java.util.Iterator;

import com.example.Listas.ListaEnlazadaDobleCircular;

public class Usuario {
    private String usuario;
    private String contrasena;
    private String nombreArchivoContactos;
    private ListaEnlazadaDobleCircular<Contacto> listaContactos;

    //Constructor de la clase
    public Usuario(String usuario, String contrasena, String nombreArchivoContactos){
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombreArchivoContactos = nombreArchivoContactos;
        this.listaContactos = new ListaEnlazadaDobleCircular<>();
    }
   
    //Generacion del Id para nuevos contactos
    public int generarCodigoUnico(){
        if (listaContactos.isEmpty()) {
            return 1; // Si la lista está vacía, el primer ID será 1
        }else{
        int actualizador = 0;
        Iterator<Contacto> iterator = this.getListaContactos().iterator();
        while (iterator.hasNext()) {
            int valor = iterator.next().getId();
            if(valor > actualizador){
                actualizador = valor;
            }
        }
        return actualizador + 1;
        }
    }

//Getters de la clase
    public ListaEnlazadaDobleCircular<Contacto> getListaContactos() {
        return listaContactos;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public String getContrasena(){
        return this.contrasena;
    }

    public String getNombreArchivo(){
        return this.nombreArchivoContactos;
    }

    //Setter del atributo listaContactos
    public void setListaContactos(ListaEnlazadaDobleCircular<Contacto> listaContactos){
        this.listaContactos = listaContactos;
    }

    //Despliegue de todos los contactos
    public void desplegarContactos(){
        Iterator<Contacto> iterator = this.getListaContactos().iterator();
            while (iterator.hasNext()) {
                iterator.next().mostrarContacto();
                System.out.println("------------------------------");
                System.out.println("");                
            }
    }
}
