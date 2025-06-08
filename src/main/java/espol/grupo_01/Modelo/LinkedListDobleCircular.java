/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.grupo_01.Modelo;

//imports para la clase
import java.util.Iterator;
import java.util.NoSuchElementException;
import javafx.collections.ObservableList;

/**
 *
 * @author Grupo 1
 */
public class LinkedListDobleCircular<E> implements Iterable<E> {
    Node<E> primero;
    Node<E> ultimo;
    int n = 0;

    
    //Constructor sin parametro de la clase
    public LinkedListDobleCircular() {    
        this.primero = null;
        this.ultimo = null;
    }

    
    //Getter
    public Node<E> getUltimo() {
        return ultimo;
    }
    
    
    //Creamos clase interna
    public class Node<E>{
        E contenido;
        Node<E> siguiente;
        Node<E> anterior;
        
        //Constructor de la clase interna
        public Node(E contenido) {
            this.contenido = contenido;
            this.siguiente = null;
            this.anterior = null;
        }
    }
    
    
    //Implementamos isEmpty() para usarlo en los metodos, que me dice si la lista esta vacia dependiendo de n
    public boolean isEmpty() {
    return n == 0;
    }
    
    
    //////Metodos implementados de Iterable  
    //Metodo que agrega un nuevo nodo al final de la lista
    public boolean add(E e) {
        Node<E> nuevoNodo = new Node<>(e);
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
            Node<E> nuevoNodo = new Node<>(element);
            Node<E> actual = primero;
            for (int i = 0; i < index; i++) {
                actual = actual.siguiente;
            }
            Node<E> anterior = actual.anterior;

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
        Node<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }
        return actual.contenido;
    }
    
    
    //Metodo que me permite eliminar un elemento de la lista y ajusta los enlaces anterior y siguiente 
    public E remove(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        }
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Node<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }
        E eliminado = actual.contenido;
        Node<E> anterior = actual.anterior;
        Node<E> siguiente = actual.siguiente;

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
    
    /*Elimina la primera ocurrencia del objeto dado y 
    Ajusta los enlaces para mantener la estructura circular y devuelve true si se eliminó.
    */
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }

        Node<E> actual = primero;
        for (int i = 0; i < n; i++) {
            if ((actual.contenido == null && o == null) || (actual.contenido != null && actual.contenido.equals(o))) {
                if (n == 1) {
                    primero = null;
                    ultimo = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                    if (actual == primero) {
                        primero = actual.siguiente;
                    }
                    if (actual == ultimo) {
                        ultimo = actual.anterior;
                    }
                }
                n--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    
    //Metodo que agrega contenido, recibendo una lista de tipo observableList de JavaFX
    public void iteracionCircular(ObservableList<String> observableList) {
        if (!isEmpty()) {
            Node<E> current = ultimo;
            do {
                observableList.add(current.contenido.toString());
                current = current.siguiente;
            } while (current != ultimo);
        }
    }    
    
    
    //Metodo anterior pero sin parametros
    public void iteracionCircular2(){
       Node<E> actual = this.ultimo;
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
            private Node<E> actual = primero;
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
    
    
    //formato para mostrar los valores, con el toString
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder resultado = new StringBuilder("[");
        Node<E> current = primero;
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

}
