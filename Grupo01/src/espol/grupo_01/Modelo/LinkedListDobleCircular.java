package espol.grupo_01.Modelo;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javafx.collections.ObservableList;
import java.io.Serializable;

public class LinkedListDobleCircular<E> implements Iterable<E>,Serializable {
    private static final long serialVersionUID = 01L;
    public CNode<E> primero;
    public CNode<E> ultimo;
    public int n = 0;

    //Constructor sin parametro de la clase
    public LinkedListDobleCircular() {    
        this.primero = null;
        this.ultimo = null;
    }

    //Getter
    public CNode<E> getUltimo() {
        return ultimo;
    }
    
    public CNode<E> getPrimero() {
    return primero;
}
  
    //Implementamos isEmpty() para usarlo en los metodos, que me dice si la lista esta vacia dependiendo de n
    public boolean isEmpty() {
    return n == 0;
    }
    
    //////Metodos implementados de Iterable  
    //Metodo que agrega un nuevo nodo al final de la lista
    public boolean add(E element){
        CNode<E> nuevoNodo = new CNode<>(element);
        if (isEmpty()) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
            nuevoNodo.siguiente = nuevoNodo;
            nuevoNodo.anterior = nuevoNodo;
        } else {
            nuevoNodo.anterior = ultimo;
            nuevoNodo.siguiente = primero;
            ultimo.siguiente = nuevoNodo;
            primero.anterior = nuevoNodo;
            ultimo = nuevoNodo;
        }
        n++;
        return true;
    }
    
    //Metodo que permite insertar un elemento en una posicion especifica de la lista
    public void add(int index, E element) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (index == n) {
            add(element); //metodo implementado anteriormente
        } else {
            CNode<E> nuevoNodo = new CNode<>(element);
            CNode<E> actual = primero;
            for (int i = 0; i < index; i++) {
                actual = actual.siguiente;
            }
            CNode<E> anterior = actual.anterior;

            nuevoNodo.anterior = anterior;
            nuevoNodo.siguiente = actual;
            actual.anterior = nuevoNodo;
            anterior.siguiente = nuevoNodo;

            if (actual == primero) {
                primero = nuevoNodo;
            }
            n++;
        }
    }

    //Metodo que nos devuelve el elemento de la lista dependiendo del indice
    public E get(int index) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        CNode<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }
        return actual.contenido;
    }
    public E remove(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        }
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        CNode<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }
        E eliminado = actual.contenido;
        CNode<E> anterior = actual.anterior;
        CNode<E> siguiente = actual.siguiente;

        if (n == 1) {
            primero = null;
            ultimo = null;
        } else if (actual == primero) {
            primero = siguiente;
            ultimo.siguiente = primero;
        } else if (actual == ultimo) {
            ultimo = anterior;
            primero.anterior = ultimo;
        } else {
            anterior.siguiente = siguiente;
            siguiente.anterior = anterior;
        }
        n--;
        return eliminado;
    }    
    //Metodo que me permite eliminar un elemento de la lista y ajusta los enlaces anterior y siguiente 
    public E remove(CNode<E> elemento) {
        if (isEmpty()) {
            throw new IllegalStateException("La lista está vacía");
        }

        if (n == 1 && primero == elemento) {
            E eliminado = elemento.contenido;
            primero = null;
            ultimo = null;
            n--;
            return eliminado;
        }

        CNode<E> actual = primero;
        boolean encontrado = false;

        do {
            if (actual == elemento) {
                encontrado = true;
                break;
            }
            actual = actual.siguiente;
        } while (actual != primero);

        if (!encontrado) {
            throw new IllegalArgumentException("El nodo no pertenece a la lista.");
        }

        E eliminado = actual.contenido;
        CNode<E> anterior = actual.anterior;
        CNode<E> siguiente = actual.siguiente;

        anterior.siguiente = siguiente;
        siguiente.anterior = anterior;

        if (actual == primero) {
            primero = siguiente;
        }
        if (actual == ultimo) {
            ultimo = anterior;
        }

        n--;
        return eliminado;
    }
    
    public boolean removeContacto(E elemento) {
        if (isEmpty()) {
            return false;
        }

        CNode<E> actual = primero;
        int cont = 0;

        do{
            if (actual.contenido.equals(elemento)) {
                remove(actual);
                return true;
            }
            actual = actual.siguiente;
            cont++;
        } while (actual != primero && cont < n);

        return false;
    }

    //Metodo que agrega contenido, recibendo una lista de tipo observableList de JavaFX
    public void iteracionCircular(ObservableList<String> observableList) {
        if (!isEmpty()) {
            CNode<E> current = ultimo;
            do {
                observableList.add(current.contenido.toString());
                current = current.siguiente;
            } while (current != ultimo);
        }
    }    
    
    //Metodo anterior pero sin parametros
    public void iteracionCircular2(){
       CNode<E> actual = this.ultimo;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(actual.contenido + " ");
            actual = actual.anterior;
        }
        System.out.println();        
    }
    
    //Metodos para avanzar al siguiente y al anterior elemento
    public void moveToNext() {
        if (!isEmpty()) {
            ultimo = ultimo.siguiente;
        }
    }
    
    public void moveToPrevious() {
        if (!isEmpty()) {
            ultimo = ultimo.anterior;
        }
    }
    
    
    //Metodo implementado para utilizar en iterator()
    public int size() {
        return n;
    }
    
    
    //Metodo que Iterator obliga implementar 
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private CNode<E> actual = primero;
            private int visitados = 0;

            @Override
            public boolean hasNext() {
                return visitados < size();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E dato = actual.contenido;
                actual = actual.siguiente;
                visitados++;
                return dato;
            }
        };
    }
    
    public void clear() {
        primero = null;
        ultimo = null;
        n = 0;
    }

    
    
    //formato para mostrar los valores, con el toString
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder resultado = new StringBuilder("[");
        CNode<E> current = primero;
        for (int i = 0; i < n; i++) {
            resultado.append(current.contenido);
            if (i < n - 1) {
                resultado.append(", ");
            }
            current = current.siguiente;
        }
        resultado.append("]");
        return resultado.toString();
    }
    
    public void set(int index, E element) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        CNode<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }
        actual.contenido = element;
    }
    
    public void swap(int i, int j) {
        E temp = get(i);
        set(i, get(j));
        set(j, temp);
    }


    
}