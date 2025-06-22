package espol.grupo_01.Modelo;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.Serializable;

public class LinkedListSimpleCircular<T> implements Iterable<T>, Serializable{
    private static final long serialVersionUID = 1L;
    private Nodo<T> cabeza;
    private int tamaño = 0;

    public void add(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
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
    public boolean contains(T dato) {
    if (isEmpty()) return false;
    Nodo<T> actual = cabeza;
    do {
        if (actual.getValor().equals(dato)) {
            return true;
        }
        actual = actual.getSiguiente();
    } while (actual != cabeza);
    return false;
    }
    
    
    public Nodo<T> getPrimero() {
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
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;
            private boolean primerPaso = true;

            @Override
            public boolean hasNext() {
                return actual != null && (primerPaso || actual != cabeza);
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = actual.valor;
                actual = actual.siguiente;
                primerPaso = false;
                return valor;
            }
        };
    }
}