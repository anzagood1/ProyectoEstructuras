package com.example.Listas;

import java.util.Iterator;

public class ListaEnlazadaDobleCircular <T> implements Iterable<T> {
    private Nodo<T> head;
    private int tamaño;
    
    public ListaEnlazadaDobleCircular(){
        this.head = null;
        this.tamaño = 0; // Suponemos que el nodo head es distinto del nodo tail
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> current = head;
            private boolean first = true;

            @Override
            public boolean hasNext() {
                return current != null && (first || current != head);
            }

            @Override
            public T next() {
                T dato = current.dato;
                current = current.next;
                first = false;
                return dato;
            }
        };
    }
    public void add(T dato){
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (head == null) {
            head = nuevoNodo;
            head.next = head;
            head.previous = head;
        }else{
            Nodo<T> tail = head.previous; 
            tail.next = nuevoNodo; // El nodo tail apunta al nuevo nodo
            nuevoNodo.previous = tail; // El nuevo nodo apunta al nodo tail
            nuevoNodo.next = head; // El nuevo nodo apunta al head
            head.previous = nuevoNodo; // Actualizamos el tail
        }
        tamaño++;
    }
    public void addFirst(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (head == null) {                 // Si la lista está vacía, el nuevo nodo es cabeza y se enlaza a sí mismo
            head = nuevoNodo;
            head.next = head;
            head.previous = head;
        } else {
            Nodo<T> cola = head.previous; // Obtener la cola desde la referencia de cabeza
            nuevoNodo.next= head;     // El nuevo nodo apunta al antiguo primer nodo
            nuevoNodo.previous = cola;        // El nuevo nodo apunta a la antigua cola
            cola.next = nuevoNodo;       // La cola apunta al nuevo nodo
            head.previous = nuevoNodo;      // El antiguo primer nodo apunta al nuevo nodo
            head = nuevoNodo;               // El nuevo nodo se convierte en la cabeza
        }
        tamaño++;
    }
    
    // Método para obtener el primer elemento de la lista
    // Complejidad O(1) ya que accede directamente al primer nodo
    public T getFirst() {
        if (head == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        return head.dato;
    }

    // Método para obtener el último elemento de la lista
    // Complejidad O(1) ya que accede directamente al último nodo
    public T getLast() {
        if (head == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        return head.previous.dato; // La cola está almacenada en cabeza.anterior
    }

    // Método para eliminar el primer elemento de la lista
    // Complejidad O(1) ya que solo cambia las referencias de cabeza, anterior y siguiente
    public T removeFirst() {
        if (head == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        T datoEliminado = head.dato;
        if (head.next == head) { // Si solo hay un elemento
            head = null;
        } else {
            Nodo<T> tail = head.previous;
            head = head.next;         // La cabeza ahora apunta al segundo nodo
            head.previous = tail;            // La nueva cabeza apunta a la cola
            tail.next = head;           // La cola apunta de nuevo a la nueva cabeza
        }
        tamaño--;
        return datoEliminado;
    }

    // Método para eliminar el último elemento de la lista
    // Complejidad O(1) ya que solo cambia la referencia de cola y del nodo anterior
    public T removeLast() {
        if (head == null) {
            throw new IllegalStateException("La lista está vacía");
        }
        T datoEliminado = head.previous.dato;
        if (head.next == head) { // Si solo hay un elemento
            head = null;
        } else {
            Nodo<T> cola = head.previous;
            cola.previous.next = head;  // El penúltimo nodo apunta a la cabeza
            head.previous = cola.previous;   // La cabeza apunta al penúltimo nodo (nueva cola)
        }
        tamaño--;
        return datoEliminado;
    }

    // Método para verificar si un elemento está en la lista
    // Complejidad O(n) ya que necesita recorrer la lista completa en el peor caso
    public boolean contains(T dato) {
        if (head == null) return false;

        Nodo<T> actual = head;
        do {
            if (actual.dato.equals(dato)) {
                return true; // Dato encontrado
            }
            actual = actual.next;
        } while (actual != head);
        return false; // Dato no encontrado
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

    // Método para imprimir la lista completa desde la cabeza hasta la cola
    // Complejidad O(n) ya que necesita recorrer toda la lista
    public void printList() {
        if (head == null) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> actual = head;
        do {
            System.out.print(actual.dato + " <-> ");
            actual = actual.next;
        } while (actual != head);
    }

    // Método para imprimir la lista completa desde la cola hasta la cabeza (recorrido inverso)
    // Complejidad O(n) ya que necesita recorrer toda la lista
    public void printListReverse() {
        if (head == null) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> actual = head.previous; // Comienza desde la cola
        do {
            System.out.print(actual.dato + " <-> ");
            actual = actual.previous;
        } while (actual != head.previous);
        System.out.println("(cabeza en reversa)"); // Indicar el final de la lista y referencia inversa
    }
    public Nodo<T> getHead() {
        return head;
    }
}

