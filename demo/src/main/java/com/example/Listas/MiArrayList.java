package com.example.Listas;

import java.lang.reflect.Array;
import java.util.Comparator;

public class MiArrayList<E> {
    public E[] array;        // Array para almacenar elementos
    private int tamaño = 0;   // Número actual de elementos en el ArrayList
    private int capacidad;    // Capacidad del ArrayList, inicializable por el usuario
    private Class<E> tipo;    // Clase de tipo genécrio para crear arrays del tipo E

    // Constructor con capacidad inicial predeterminada (10)
    public MiArrayList(Class<E> tipo) {
        this(tipo, 10); // Llama al constructor con capacidad personalizada
    }

    // Constructor que permite especificar una capacidad inicial personalizada
    @SuppressWarnings("unchecked")
    public MiArrayList(Class<E> tipo, int capacidadInicial) {
        this.tipo = tipo;
        this.capacidad = capacidadInicial;
        this.array = (E[]) Array.newInstance(tipo, capacidad); // Crear un array de tipo E con la capacidad inicial
    }

    // Método para añadir un elemento al final de la lista
    // Complejidad O(1) en promedio, O(n) en el peor caso cuando se necesita expandir
    public void add(E elemento) {
        if (tamaño == capacidad) {
            expandirCapacidad(); // Expandir si se alcanza la capacidad
        }
        array[tamaño] = elemento; // Añadir el elemento al final
        tamaño++; // Incrementar el tamaño
    }

    // Método para añadir un elemento en una posición específica
    // Complejidad O(n) debido al desplazamiento de elementos
    public void add(int index, E elemento) {
        if (index < 0 || index > tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (tamaño == capacidad) {
            expandirCapacidad(); // Expandir si se alcanza la capacidad
        }
        // Desplazar elementos a la derecha para hacer espacio
        for (int i = tamaño; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = elemento; // Insertar el nuevo elemento
        tamaño++;
    }

    // Método para obtener el elemento en una posición específica
    // Complejidad O(1) ya que accede directamente al índice
    public E get(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return array[index];
    }

    // Método para establecer un nuevo valor en una posición específica
    // Complejidad O(1) ya que accede directamente al índice
    public void set(int index, E elemento) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        array[index] = elemento;
    }

    // Método para eliminar un elemento en una posición específica
    // Complejidad O(n) debido al desplazamiento de elementos hacia la izquierda
    public E remove(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        E elementoEliminado = array[index];
        // Desplazar elementos a la izquierda para llenar el espacio vacío
        for (int i = index; i < tamaño - 1; i++) {
            array[i] = array[i + 1];
        }
        array[tamaño - 1] = null; // Eliminar la referencia del último elemento
        tamaño--;
        return elementoEliminado; // Devolver el elemento eliminado
    }

    // Método para verificar si un elemento está en la lista
    // Complejidad O(n) ya que necesita recorrer la lista en el peor caso
    public boolean contains(E elemento) {
        for (int i = 0; i < tamaño; i++) {
            if (array[i].equals(elemento)) {
                return true; // Elemento encontrado
            }
        }
        return false; // Elemento no encontrado
    }

    // Método para obtener el tamaño actual de la lista
    // Complejidad O(1) ya que solo devuelve el valor del atributo tamaño
    public int size() {
        return tamaño;
    }

    // Método para verificar si la lista está vacía
    // Complejidad O(1) ya que solo verifica si el tamaño es igual a 0
    public boolean isEmpty() {
        return tamaño == 0;
    }

    // Método para expandir la capacidad del ArrayList cuando se llena
    // Complejidad O(n) ya que necesita copiar todos los elementos al nuevo array
    @SuppressWarnings("unchecked")
    private void expandirCapacidad() {
        capacidad = capacidad + (capacidad / 2); // Incremento del 50%
        E[] nuevoArray = (E[]) Array.newInstance(tipo, capacidad); // Crear un nuevo array de tipo E con capacidad expandida
        // Copiar elementos del array antiguo al nuevo
        for (int i = 0; i < tamaño; i++) {
            nuevoArray[i] = array[i];
        }
        array = nuevoArray; // Reemplazar el array antiguo por el nuevo
    }

    public void printList() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void sort(Comparator<E> comparador) {
    for (int i = 0; i < tamaño - 1; i++) {
        for (int j = 0; j < tamaño - i - 1; j++) {
            if (comparador.compare(array[j], array[j + 1]) > 0) {
                E temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
}
}

