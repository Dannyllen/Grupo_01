package espol.grupo_01.Modelo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListSimpleCircular<T> implements Iterable<String> {

    private Nodo<T> cabeza;
    private int tamaño = 0;

    public void add(T elemento, T identificador) {
        Nodo<T> nuevo = new Nodo<>(elemento, identificador);
        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza; 
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != cabeza) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
            nuevo.siguiente = cabeza; 
        }
        tamaño++;
    }
    
    public Nodo<T> getPrimero(){
        return cabeza;
    }

    public T get(int index) {
        if (index < 0 || index >= tamaño) throw new IndexOutOfBoundsException();
        Nodo<T> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    public int size() {
        return tamaño;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

    public void clear() {
        cabeza = null;
        tamaño = 0;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private Nodo<T> actual = cabeza;
            private boolean primerPaso = true;

            @Override
            public boolean hasNext() {
                return actual != null && (primerPaso || actual != cabeza);
            }

            @Override
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                String valor = actual.identificador + ": " + actual.valor;
                actual = actual.siguiente;
                primerPaso = false;
                return valor;
            }
        };
    }
}
