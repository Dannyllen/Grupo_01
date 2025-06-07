/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.grupo_01.Modelo;

/**
 *
 * @author Grupo 1
 */
public class ListaCircular<E> {
    private E contenido;
    private ListaCircular<E> siguiente;
    private ListaCircular<E> anterior;
    
    
    //Constructor de clase
    public ListaCircular (E elemento){
        this.contenido= elemento;        
    }
    
    
    //Constructor sin parametros
    public ListaCircular(){}
    
    
    //Getters
    public E getContent() {
        return contenido;
    }

    public ListaCircular<E> getSig() {
        return siguiente;
    }

    public ListaCircular<E> getPrev() {
        return anterior;
    }

    
    //Setters
    public void setContent(E content) {
        this.contenido = content;
    }

    public void setSig(ListaCircular<E> next) {
        this.siguiente = next;
    }

    public void setPrev(ListaCircular<E> previous) {
        this.anterior = previous;
    }
}
