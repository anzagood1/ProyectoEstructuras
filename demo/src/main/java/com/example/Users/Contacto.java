package com.example.Users;

import java.util.Map;

public abstract class Contacto{
    protected String nombre;
    protected int id;
    protected Map<String, String> telefonos;
    protected Map<String, String> direcciones;
    protected Map<String, String> redesSociales;
    protected Map<String, String> fechasDeInteres;

    //Constructor de la clase
    public Contacto(int id, Map<String, String> telefonos, Map<String, String> direcciones,Map<String, String> redesSociales, Map<String, String> fechasDeInteres) {
        this.id = id;
        this.telefonos = telefonos;
        this.direcciones = direcciones;
        this.fechasDeInteres = fechasDeInteres;
        this.redesSociales = redesSociales;
    }

    //Agrega un key y value al mapa telefonos
    public void agregarTelefono(String descripcion, String numero){
        telefonos.put(numero, descripcion);
    }

    //Agrega un key y value al mapa telefonos
    public void agregarDirecciones(String descripcion, String mail){
        direcciones.put(mail, descripcion);
    }

    //Agrega un key y value al mapa telefonos
    public void agregarRedesSociales(String red, String cuenta){
        redesSociales.put(cuenta, red);
    }

    //Agrega un key y value al mapa telefonos
    public void agregarFechasDeInteres(String descripcion, String fecha){
        fechasDeInteres.put(descripcion, fecha);
    }

    //Getters públicos para acceder a los campos protegidos
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getTelefonos() {
        return telefonos;
    }

    public Map<String, String> getDirecciones() {
        return direcciones;
    }

    public Map<String, String> getRedesSociales() {
        return redesSociales;
    }

    public Map<String, String> getFechasDeInteres() {
        return fechasDeInteres;
    }

    //Setter del atributo nombre
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    //Metodo abstracto
    public abstract void mostrarContacto();
    
}