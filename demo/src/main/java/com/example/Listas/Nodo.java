package com.example.Listas;



public class Nodo <T> {
    public T dato; 
    public Nodo<T>  next;
    public Nodo<T> previous; 

    public Nodo(T dato){
        this.dato = dato;
        this.next = null;
        this.previous = null;
    }
    @Override
    public String toString(){
        return dato.toString();
    }
}
